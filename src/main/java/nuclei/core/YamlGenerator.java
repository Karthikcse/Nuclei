package nuclei.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nuclei.service.BlueprintService;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class YamlGenerator {

	public final String FILEPATH = "./src/main/resources/NewBosh.yml";
	//public final String FILEPATH = "WEB-INF/classes/NewBosh.yml";

	@Autowired
	private BlueprintService blueprintService;

	@SuppressWarnings("rawtypes")
	public void generateYaml(Long blueprintId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = blueprintService.generateYamlFile(blueprintId);
			//System.out.println("entity.......>\n" + entity);

			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();

					blueprintArray = (ArrayList) blueprintList.get("blueprint");
					yamlCollection = createYamlCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void writeYamlFile(Map<String, Object> yamlCollection) throws IOException{

		File file = new File(FILEPATH);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file);

		fw.write("---");	

		try{
			String nameAttibute = getAttibuteValue(yamlCollection,"release", "name" );
			fw.write("\n"+"name"+ ": " + nameAttibute);
			String uuidAttibute = getAttibuteValue(yamlCollection,"cloud", "director_uuid" );
			fw.write("\n"+"director_uuid"+ ": " + uuidAttibute);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

		for (Map.Entry<String, Object> entry : yamlCollection.entrySet())
		{			
			if (entry.getValue() != null) {

				fw.write("\n\n" + entry.getKey() + ":");
				boolean arrayFlag = false;					

				List<JSONObject> relationValues = (List<JSONObject>)entry.getValue();

				for (Iterator iterator = relationValues.iterator(); iterator.hasNext();) {
					if(relationValues!=null && relationValues.size()>1){
						arrayFlag = true;
					}
					JSONObject jsonObject = (JSONObject) iterator.next();
					if(jsonObject!=null && jsonObject.get("value")!=null)
					{
						String valueString = jsonObject.get("value").toString();

						if(valueString.length() > 0){							
							HashMap<String,Object> result = new ObjectMapper().readValue(valueString, HashMap.class);
							Iterator it = result.entrySet().iterator();

							if(arrayFlag){
								fw.write("\n" + " -");
							}

							while (it.hasNext()) {
								Map.Entry pair = (Map.Entry) it.next();	

								if(pair.getValue() instanceof ArrayList){

									String newline = (arrayFlag?" ":"\n"+" ") ;
									fw.write(newline);
									fw.write("  "+pair.getKey()+ ": ");
									arrayFlag = false;

									ArrayList valueList = (ArrayList)pair.getValue();

									for (int i = 0; i < valueList.size(); i++) {

										String value=valueList.get(i).toString();
										value = value.substring(1, value.length()-1);

										if(value.contains("[") && value.contains("]")){
											//System.out.println("value :"+value);
											String subarray = value.substring(value.lastIndexOf("["), value.lastIndexOf("]"));							

											if(value.contains("[{")){
												String startValue=value.substring(0,value.indexOf("="));										
												fw.write("\n    "+startValue+" :");
												String subarrayValue=subarray.replace("[{","");
												String  subarrayValues=subarrayValue.replace("}","");
												writeInnerJson(subarrayValues, fw);
											}
											else{
												//System.out.println("value.."+value);
												String[] split1=value.split("\\,",10);		
												//System.out.println("\nlen....."+split1.length);
												for (String num: split1)
												{
													String[] pairEntry = num.split("="); 
													fw.write("\n     ");
													//System.out.println("\n1...."+pairEntry[0].trim()+"\n2..."+pairEntry[1].trim());
													fw.write(pairEntry[0].trim()+ ": " + ((Integer.valueOf(pairEntry.length)>1)?pairEntry[1].trim():""));
												}											
											}

										}
										else{
											writeInnerJson(value, fw);
										}
									}
								}
								else{
									String newline = (arrayFlag?" ":"\n"+"   ") ;
									fw.write(newline);
									fw.write(pair.getKey()+ ": " + pair.getValue());
									arrayFlag = false;
								}
							}
						}
					}
				}
			}
		}

		fw.close();
	}

	public void writeInnerJson(String value, FileWriter fw) throws IOException{		
		//System.out.println("value.."+value);
		String[] keyValuePairs = value.split(",");
		//System.out.println("\nkeyValuePairs......."+keyValuePairs.length);
		for(String pairValue : keyValuePairs)                    
		{ 
			String[] pairEntry = pairValue.split("=");  

			fw.write("\n     ");
			fw.write(pairEntry[0].trim()+ ": " + ((Integer.valueOf(pairEntry.length)>1)?pairEntry[1].trim():""));
			//			/System.out.println("\nval..."+value+"\npairEntry.length..."+pairEntry.length);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> createYamlCollection(ArrayList blueprintArray, Map<String, Object> yamlCollection){

		ArrayList taskArray = new ArrayList();
		ArrayList relationArray = new ArrayList();
		ArrayList relationParamArray = new ArrayList();

		HashMap blueprintObj = new HashMap();
		HashMap taskValue = new HashMap();
		HashMap relationParamValue = new HashMap();

		List<JSONObject> relationValues = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();

		blueprintObj = (HashMap) blueprintArray.get(0);

		taskArray = (ArrayList) blueprintObj.get("task");
		taskValue = (HashMap) taskArray.get(0);	

		String key = (String)taskValue.get("task");

		relationArray = (ArrayList) taskValue.get("relation");
		if(relationArray!=null){
			relationParamArray = (ArrayList)((HashMap) relationArray.get(0)).get("relationParam");
			if (relationParamArray != null && relationParamArray.size()>0) {

				relationParamValue = (HashMap) relationParamArray.get(0);
				json.putAll( relationParamValue );
				relationValues.add(json);
			}
		}
		relationArray = (ArrayList) taskValue.get("ConfigRelation");
		if(relationArray!=null){
			relationParamArray = (ArrayList)((HashMap) relationArray.get(0)).get("relationParam");
			if (relationParamArray != null && relationParamArray.size()>0) {

				relationParamValue = (HashMap) relationParamArray.get(0);
				json.putAll( relationParamValue );
				relationValues.add(json);
			}
		}

		if(yamlCollection.containsKey(key)){
			relationValues.addAll((List<JSONObject>)yamlCollection.get(key));
			yamlCollection.put(key, relationParamValue);
		}
		yamlCollection.put(key, relationValues);

		return yamlCollection;
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String getAttibuteValue(Map<String, Object> yamlCollection,String task,String attribute) throws JsonParseException, JsonMappingException, IOException{
		///System.out.println("yamlCollection ::"+yamlCollection);
		Object obj = yamlCollection.get(task);
		if(obj instanceof List){
			List<JSONObject> relationValues = (List<JSONObject>)obj;	

			for (Iterator iterator = relationValues.iterator(); iterator.hasNext();) {

				JSONObject jsonObject = (JSONObject) iterator.next();
				if(jsonObject!=null && jsonObject.get("value")!=null)
				{
					String valueString = jsonObject.get("value").toString();
					if(valueString.length() > 0){
						HashMap<String,Object> result = new ObjectMapper().readValue(valueString, HashMap.class);
						Iterator it = result.entrySet().iterator();				

						while (it.hasNext()) {
							Map.Entry pair = (Map.Entry) it.next();	

							if (pair.getKey().equals(attribute)) {
								return pair.getValue().toString();
							}

						}
					}
				}			
			}

		}
		return null;
	}

}
