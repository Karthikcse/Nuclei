package nuclei.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nuclei.controller.TransactionController;
import nuclei.controller.TransactionLifecycleController;
import nuclei.service.IaaSTemplateService;
import nuclei.service.TransactionLifecycleService;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yaml.snakeyaml.Yaml;

import java.util.UUID;

@Controller
public class TransactionListGenerator {

	@Autowired
	private IaaSTemplateService templateService;

	@Autowired
	private TransactionController transactionController;

	@Autowired
	private TransactionLifecycleService transactionLifecycleService;	

	@Autowired
	private TransactionLifecycleController transactionLifecycleController;

	@Autowired
	private YamlGenerator yaml;	

	@SuppressWarnings("rawtypes")
	public void generateCLI(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshTarget(templateId);
			//	System.out.println("entity.......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		try{
			getBoshLogin(templateId,deployId);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try{
			boshDeploy(templateId,deployId);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try{
			boshRelease(templateId,deployId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try{
			boshDeployment(templateId,deployId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try{
			boshStemcell(templateId,deployId);	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//bosh login
	@SuppressWarnings("rawtypes")
	public void getBoshLogin(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshLogin(templateId);
			//System.out.println("getIaaSTemplateSequence .......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//boshDeploy
	@SuppressWarnings("rawtypes")
	public void boshDeploy(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshDeploy(templateId);
			//System.out.println("getIaaSTemplateSequence .......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//boshRelease
	@SuppressWarnings("rawtypes")
	public void boshRelease(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshRelease(templateId);
			//System.out.println("getIaaSTemplateSequence .......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//boshDeployment
	@SuppressWarnings("rawtypes")
	public void boshDeployment(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshDeployment(templateId);
			//System.out.println("getIaaSTemplateSequence .......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//bosh upload stemcell
	@SuppressWarnings("rawtypes")
	public void boshStemcell(Long templateId,String deployId) {
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = templateService.getBoshUploadStemcell(templateId);
			//System.out.println("1. stemcell .......>\n" + entity);
			ArrayList blueprintArray = new ArrayList();	
			HashMap blueprintList = new HashMap();
			Map<String, Object> yamlCollection = new HashMap<String, Object>();

			for (Map<String, Object> map : entity) {
				for (Entry<String, Object> entry : map.entrySet()) {
					blueprintList = (HashMap) entry.getValue();
					blueprintArray = (ArrayList) blueprintList.get("iaasTemplate");
					yamlCollection = createLoginTargetCollection(blueprintArray, yamlCollection);
					writeYamlFile(yamlCollection,deployId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void writeYamlFile(Map<String, Object> yamlCollection,String deployId) throws IOException{	
		String yamlPath=yaml.FILEPATH;
		
		try{
			String boshTarget="bosh target";
			Map<String, Object> nameAttibute = getLoginTargetValue(yamlCollection,boshTarget, "target" );

			String boshLoginUser="bosh login";
			Map<String, Object> loginUserName = getLoginTargetValue(yamlCollection,boshLoginUser, "UID" );

			String boshLoginPass="bosh login";
			Map<String, Object> loginPassword = getLoginTargetValue(yamlCollection,boshLoginPass, "PSW" );

			String boshDeploy="bosh deploy";
			Map<String, Object> boshDeployAttrib = getboshFunctCollection(yamlCollection,boshDeploy);

			String boshRelease="bosh upload-release";
			Map<String, Object> boshReleaseAttrib = getboshFunctCollection(yamlCollection,boshRelease);

			String boshDeployment="bosh deployment";
			Map<String, Object> boshDeploymentAttrib = getboshFunctCollection(yamlCollection,boshDeployment);

			String boshStemcell="bosh upload-stemcell";
			Map<String, Object> boshStemcellAttrib=getboshFunctCollection(yamlCollection,boshStemcell);

			//bosh target

			if(nameAttibute!=null && !nameAttibute.isEmpty()){	

				String targetId="";
				String targetSeq="";
				String command_type="";
				String command="";
				String parameters="";
				String functionName="";
				Iterator nameIt=nameAttibute.entrySet().iterator();
				while(nameIt.hasNext()){				
					Map.Entry me=(Entry) nameIt.next();
					if(me.getKey().equals("functionId")){
						targetId=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						targetSeq=me.getValue().toString();
					}
					if(me.getKey().equals("name")){
						parameters=me.getValue().toString();
					}
					if(me.getKey().equals("functionName")){
						functionName=me.getValue().toString();
					}				
				}	

				if(nameAttibute!=null && !nameAttibute.isEmpty()){	
					UUID uuid = UUID.randomUUID();   
					String randomUUID = uuid.toString();
					ArrayList ar=new ArrayList();					
					String[] keyValuePairs = functionName.split(" ");
					for(String pairValue : keyValuePairs)                    
					{ 
						ar.add(pairValue);				
					}
					command_type=ar.get(0).toString();
					command=ar.get(1).toString();					
					transactionController.functionToTransaction(targetId,command,randomUUID,command_type,parameters,targetSeq,deployId);				
				}
			}						

			//bosh login
			String loginID="";		
			String loginSeq="";
			String user="";
			String pass="";
			String login="";
			String loginfunctionName="";
			String loginparameters="";
			String logincommand_type="";
			String logincommand="";

			if(loginUserName!=null){				


				Iterator userIt=loginUserName.entrySet().iterator();
				while(userIt.hasNext()){
					Map.Entry me=(Entry) userIt.next();
					if(me.getKey().equals("functionId")){
						loginID=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						loginSeq=me.getValue().toString();
					}
					if(me.getKey().equals("name")){
						user=me.getValue().toString();
					}		
					if(me.getKey().equals("functionName")){
						loginfunctionName=me.getValue().toString();
					}	
				}				
			}

			if(loginPassword!=null){	
				Iterator passIt=loginPassword.entrySet().iterator();
				while(passIt.hasNext()){
					Map.Entry me=(Entry) passIt.next();

					if(me.getKey().equals("name")){
						pass=me.getValue().toString();
					}				
				}
			}

			loginparameters="{\"UID\":\""+user+"\",\"PSW\":\""+pass+"\"}";//	user+" "+pass;

			if(!user.isEmpty() || !pass.isEmpty() || !login.isEmpty() && loginUserName!=null && !loginUserName.isEmpty() && loginPassword!=null && !loginPassword.isEmpty()){				
				UUID uuid = UUID.randomUUID();				 
				String randomUUID = uuid.toString();
				ArrayList ar=new ArrayList();					
				String[] keyValuePairs = loginfunctionName.split(" ");
				for(String pairValue : keyValuePairs)                    
				{ 
					ar.add(pairValue);				
				}
				logincommand_type=ar.get(0).toString();
				logincommand=ar.get(1).toString();					
				transactionController.functionToTransaction(loginID,logincommand,randomUUID,logincommand_type,loginparameters,loginSeq,deployId);			
			}	

			//bosh deploy					
			if(boshDeployAttrib!=null){	
				String yamlJson=yamlToJson();
				String boshDeployID="";
				String boshDeploySeq="";			
				String command_type="";
				String command="";			
				String functionName="";
				Iterator nameIt=boshDeployAttrib.entrySet().iterator();
				while(nameIt.hasNext()){
					Map.Entry me=(Entry) nameIt.next();
					if(me.getKey().equals("functionId")){
						boshDeployID=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						boshDeploySeq=me.getValue().toString();
					}
					if(me.getKey().equals("functionName")){
						functionName=me.getValue().toString();
					}	
				}
				if(!boshDeploySeq.isEmpty()){	
					UUID uuid = UUID.randomUUID();   
					String randomUUID = uuid.toString();
					ArrayList ar=new ArrayList();					
					String[] keyValuePairs = functionName.split(" ");
					for(String pairValue : keyValuePairs)                    
					{ 
						ar.add(pairValue);				
					}
					command_type=ar.get(0).toString();
					command=ar.get(1).toString();					
					transactionController.functionToTransaction(boshDeployID,command,randomUUID,command_type,yamlJson,boshDeploySeq,deployId);			
				}	
			}						

			//bosh upload release 

			if(boshReleaseAttrib!=null){	
				String releaseID="";
				String releaseSeq="";
				String command_type="";
				String command="";
				String parameters="";
				String functionName="";

				Iterator nameIt=boshReleaseAttrib.entrySet().iterator();
				while(nameIt.hasNext()){
					Map.Entry me=(Entry) nameIt.next();
					if(me.getKey().equals("functionId")){
						releaseID=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						releaseSeq=me.getValue().toString();
					}
					if(me.getKey().equals("functionName")){
						functionName=me.getValue().toString();
					}				
				}
				if(!releaseSeq.isEmpty()){	
					UUID uuid = UUID.randomUUID();   
					String randomUUID = uuid.toString();
					ArrayList ar=new ArrayList();					
					String[] keyValuePairs = functionName.split(" ");
					for(String pairValue : keyValuePairs)                    
					{ 
						ar.add(pairValue);				
					}
					command_type=ar.get(0).toString();
					command=ar.get(1).toString();					
					transactionController.functionToTransaction(releaseID,command,randomUUID,command_type,parameters,releaseSeq,deployId);				
				}
			}	

			//bosh deployment 

			if(boshDeploymentAttrib!=null){	
				String deploymentID="";
				String deploymentSeq="";					
				String command_type="";
				String command="";
				String parameters="";
				String functionName="";
				Iterator nameIt=boshDeploymentAttrib.entrySet().iterator();
				while(nameIt.hasNext()){
					Map.Entry me=(Entry) nameIt.next();
					if(me.getKey().equals("functionId")){
						deploymentID=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						deploymentSeq=me.getValue().toString();
					}						
					if(!yamlPath.isEmpty()){
						parameters=yamlPath;
					}
					if(me.getKey().equals("functionName")){
						functionName=me.getValue().toString();
					}	
				}
				//deploymentName=value2+" "+value1;
				if(!deploymentSeq.isEmpty()){	
					UUID uuid = UUID.randomUUID();   
					String randomUUID = uuid.toString();
					ArrayList ar=new ArrayList();					
					String[] keyValuePairs = functionName.split(" ");
					for(String pairValue : keyValuePairs)                    
					{ 
						ar.add(pairValue);				
					}
					command_type=ar.get(0).toString();
					command=ar.get(1).toString();					
					transactionController.functionToTransaction(deploymentID,command,randomUUID,command_type,parameters,deploymentSeq,deployId);				

				}
			}

			//bosh upload stemcell
			if(boshStemcellAttrib!=null){	

				//	String stemcellName="";
				String stemcellID="";
				String stemcellSeq="";
				String command_type="";
				String command="";
				String parameters="";
				String functionName="";

				for (Map.Entry<String, Object> entry : yamlCollection.entrySet())
				{	
					if (entry.getValue() != null) {

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
								Map<String,String> map1 = new HashMap<>();

								HashMap<String,Object> result1 = new ObjectMapper().readValue(valueString, HashMap.class);
								Iterator it = result1.entrySet().iterator();						

								while (it.hasNext()) {
									Map.Entry pair = (Map.Entry) it.next();							
									if(pair.getValue() instanceof ArrayList){

										String newline = "\n"+"	  " ;							
										arrayFlag = false;

										ArrayList valueList = (ArrayList)pair.getValue();

										for (int i = 0; i < valueList.size(); i++) {

											String strValue=valueList.get(i).toString();
											strValue = strValue.substring(1, strValue.length()-1);

											String[] split1=strValue.split(",", 2);;											
											for (String num: split1)
											{
												String[] pairEntry = num.split("="); 	HashMap hm=new HashMap();
												hm.put(pairEntry[0].trim(), ((Integer.valueOf(pairEntry.length)>1)?pairEntry[1].trim():""));
												Iterator iter=hm.entrySet().iterator();
												while(iter.hasNext()){
													Map.Entry me=(Entry) iter.next();													
													if(me.getKey().equals("name")){
														parameters=me.getValue().toString();
													}
												}
											}	

										}
									}
									else{
										String newline = (arrayFlag?" ":"\n"+"	  ") ;								
										arrayFlag = false;
									}
								}	
							}
						}
					}
				}

				Iterator nameIt=boshStemcellAttrib.entrySet().iterator();
				while(nameIt.hasNext()){
					Map.Entry me=(Entry) nameIt.next();

					if(me.getKey().equals("functionId")){
						stemcellID=me.getValue().toString();
					}
					if(me.getKey().equals("sequence")){
						stemcellSeq=me.getValue().toString();
					}	
					if(me.getKey().equals("functionName")){
						functionName=me.getValue().toString();
					}	
				}
				//	stemcellValue=value+ " "+stemcellName;
				if(!stemcellSeq.isEmpty()){	
					UUID uuid = UUID.randomUUID();   
					String randomUUID = uuid.toString();
					ArrayList ar=new ArrayList();					
					String[] keyValuePairs = functionName.split(" ");
					for(String pairValue : keyValuePairs)                    
					{ 
						ar.add(pairValue);				
					}
					command_type=ar.get(0).toString();
					command=ar.get(1).toString();					
					transactionController.functionToTransaction(stemcellID,command,randomUUID,command_type,parameters,stemcellSeq,deployId);			
				}	
			}		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> createLoginTargetCollection(ArrayList blueprintArray, Map<String, Object> yamlCollection){

		ArrayList functionArray = new ArrayList();
		ArrayList relationArray = new ArrayList();
		ArrayList iaasconfigArray = new ArrayList();
		ArrayList taskArray = new ArrayList();
		ArrayList taskRelationArray = new ArrayList();

		HashMap templateObj = new HashMap();
		HashMap functionValue = new HashMap();
		HashMap iaasconfigValue = new HashMap();
		HashMap relationValue = new HashMap();
		HashMap taskValue = new HashMap();

		List<JSONObject> taskValues = new ArrayList<JSONObject>();
		JSONObject json = new JSONObject();

		templateObj = (HashMap) blueprintArray.get(0);

		functionArray = (ArrayList) templateObj.get("function");
		functionValue = (HashMap) functionArray.get(0);	

		String key = (String)functionValue.get("functionName");
		int funcId=(int)functionValue.get("id");
		String functionId = String.valueOf(funcId);

		relationArray = (ArrayList) functionValue.get("relation");		

		if (relationArray != null && relationArray.size()>0) {
			relationValue=(HashMap) relationArray.get(0);
			json.putAll( relationValue );
			taskValues.add(json);
		}

		json.put("functionId",functionId);
		json.put("functionName",key);
		taskValues.add(json);

		if(relationValue.containsKey("iaasConfig")){
			iaasconfigArray = (ArrayList) relationValue.get("iaasConfig");
			iaasconfigValue=(HashMap) iaasconfigArray.get(0);
			taskArray=(ArrayList) iaasconfigValue.get("tasks");
			if(taskArray!=null){
				taskRelationArray = (ArrayList)((HashMap) taskArray.get(0)).get("taskRelation");
				if (taskRelationArray != null && taskRelationArray.size()>0) {
					taskValue = (HashMap) taskRelationArray.get(0);
					json.putAll( taskValue );				
					taskValues.add(json);
				}
			}	
		}		
		if(relationValue.containsKey("tasks")){		
			taskArray=(ArrayList) relationValue.get("tasks");
			if(taskArray!=null){
				taskRelationArray = (ArrayList)((HashMap) taskArray.get(0)).get("taskRelation");
				if (taskRelationArray != null && taskRelationArray.size()>0) {
					taskValue = (HashMap) taskRelationArray.get(0);
					json.putAll( taskValue );				
					taskValues.add(json);
				}
			}	
		}
		if(yamlCollection.containsKey(key)){
			taskValues.addAll((List<JSONObject>)yamlCollection.get(key));
			yamlCollection.put(key, taskValue);
		}
		yamlCollection.put(key, taskValues);
		return yamlCollection;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getLoginTargetValue(Map<String, Object> yamlCollection,String task,String attribute) throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> collectValues=new HashMap();
		Object obj = yamlCollection.get(task);
		if(obj instanceof List){
			List<JSONObject> relationValues = (List<JSONObject>)obj;	

			for (Iterator iterator = relationValues.iterator(); iterator.hasNext();) {

				JSONObject jsonObject = (JSONObject) iterator.next();

				String val=jsonObject.get("sequence_num").toString();				
				collectValues.put("sequence",val);

				String functId= jsonObject.get("functionId").toString();
				collectValues.put("functionId",functId);

				String functName= jsonObject.get("functionName").toString();
				collectValues.put("functionName",functName);

				if(jsonObject!=null && jsonObject.get("value")!=null){
					String valueString = jsonObject.get("value").toString();
					HashMap<String,Object> result = new ObjectMapper().readValue(valueString, HashMap.class);
					Iterator it = result.entrySet().iterator();	
					while (it.hasNext()) {
						Map.Entry pair = (Map.Entry) it.next();	
						if (pair.getKey().equals(attribute)) {
							//String value=pair.getValue().toString();							
							collectValues.put("name", pair.getValue());								
							return collectValues;
						}
					}					
				}
			}
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> getboshFunctCollection(Map<String, Object> yamlCollection,String task) throws JsonParseException, JsonMappingException, IOException{
		Map<String, Object> collectValues=new HashMap();
	/*	String value=null;*/
		Object obj = yamlCollection.get(task);
		if(obj instanceof List){
			List<JSONObject> relationValues = (List<JSONObject>)obj;	

			for (Iterator iterator = relationValues.iterator(); iterator.hasNext();) {
				JSONObject jsonObject = (JSONObject) iterator.next();

				String val=jsonObject.get("sequence_num").toString();				
				collectValues.put("sequence",val);

				String functId= jsonObject.get("functionId").toString();
				collectValues.put("functionId",functId);			

				String functName= jsonObject.get("functionName").toString();
				collectValues.put("functionName",functName);

				return collectValues;
			}
		}
		return null;
	}	

	@SuppressWarnings("unchecked")
	public String yamlToJson() throws IOException{	
		String yamlPath=yaml.FILEPATH;		
		BufferedReader br = new BufferedReader(new FileReader(yamlPath));
		String resultString="";
		Yaml yaml= new Yaml();		
		try {			
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			resultString=sb.toString();			
			Map<String,Object> map= (Map<String, Object>) yaml.load(resultString);
			JSONObject jsonObject=new JSONObject(map);	
			String resultJson=jsonObject.toString();
			return resultJson;	
		} finally {
			br.close();			
		}
	}
}
