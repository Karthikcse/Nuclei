/**
 * 
 */
package nuclei.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuclei.core.YamlGenerator;
import nuclei.domain.CatalogueBlueprint;
import nuclei.domain.IaaSTemplate;
import nuclei.domain.IaasParameters;
import nuclei.domain.ScTaxonomy;
import nuclei.domain.Tasks;
import nuclei.domain.Blueprint;
import nuclei.response.BlueprintMessage;
import nuclei.response.BlueprintsMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.service.CatalogueBlueprintService;
import nuclei.service.MainService;
import nuclei.service.BlueprintService;
import nuclei.service.ScTaxonomyService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.multipart.FormDataParam;

/**
 * @author Karthikeyan
 *
 */

// @RestController
@Controller
public class BlueprintController extends MainController<Blueprint> {

	@Autowired
	private CatalogueBlueprintService catalogueBlueprintService;
	
	@Autowired
	private ScTaxonomyService scTaxonomyService;
	
	@Autowired
	private BlueprintService blueprintService;
	
	@Autowired
	private YamlGenerator yamlGenerator;

	@RequestMapping(value = "/blueprints", method = RequestMethod.GET)
	public @ResponseBody BlueprintsMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = blueprintService.getTemplateBlueprints();
			if (entity != null) {
				status = new ResponseStatus(ResponseStatusCode.STATUS_OK,
						"SUCCESS");
			} else {
				status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,
						"No Record found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintsMessage(status, entity);
	}

	@RequestMapping(value = "/createBlueprint", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage create(HttpServletRequest request,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Blueprint entity = new Blueprint();
		Blueprint blueprintParam = null;
		IaasParameters params = null;
		List<IaasParameters> paramsList = new ArrayList<IaasParameters>();
		IaaSTemplate iaas = null;
		String[] responseValues = null;
		try {
			String urlValues = URLDecoder.decode(request.getQueryString(),
					"UTF-8");
			System.out.println("urlValues-->" + urlValues);
			responseValues = urlValues.split("=");
			System.out.println("responseValues>>>" + responseValues[0]
					+ " responseValues >> " + responseValues[1]);
			JSONObject blueprintObj = (JSONObject) new JSONParser()
					.parse(responseValues[1]);
			Long id = Long.parseLong((String) blueprintObj.get("id"));
			iaas = blueprintService.getIaasTemplate(id);
			String blueprint = (String) blueprintObj.get("blueprint");
			String onIaas = (String) blueprintObj.get("onIaas");
			String script = (String) blueprintObj.get("script");

			entity.setBlueprint(blueprint);
			entity.setOnIaas(onIaas);
			entity.setScript(script);
			entity.setIsDeleted("0");

			entity.setIaaSTemplate(iaas);
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);

			blueprintParam = blueprintService.find(entity.getId());

			JSONArray paramValues = (JSONArray) blueprintObj
					.get("iaasParameters");

			for (int i = 0; i < paramValues.size(); i++) {
				params = new IaasParameters();

				JSONObject paramObj = (JSONObject) paramValues.get(i);
				String iaasConfig = (String) paramObj.get("iaasConfig");

				params.setIaasConfig(iaasConfig);
				params.setIsDeleted("0");
				paramsList.add(params);
			}

			blueprintParam.setIaasParameters(paramsList);
			response.setHeader("Cache-Control", "no-cache");
			super.update(entity.getId(), blueprintParam);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			status = new ResponseStatus(
					ResponseStatusCode.STATUS_INTERNAL_ERROR, "INTERNAL ERROR");
		}
		return new BlueprintMessage(status, entity);
	}

	@RequestMapping(value = "/addParamsByBlueprintId", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage addParameters(
			@FormDataParam("id") String id,
			@FormDataParam("iaasConfig") String iaasConfig,
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		List<IaasParameters> paramsList = new ArrayList<IaasParameters>();
		IaasParameters params = new IaasParameters();
		Blueprint blueprint = null;
		try {
			blueprint = blueprintService.find(blueprintId);
			params.setIaasConfig(iaasConfig);
			params.setIsDeleted("0");
			paramsList.add(params);
			blueprint.setIaasParameters(paramsList);
			response.setHeader("Cache-Control", "no-cache");
			super.update(blueprintId, blueprint);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, blueprint);
	}

	@RequestMapping(value = "/addTaskByBlueprintId", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage addTasks(
			@FormDataParam("id") String id, @FormDataParam("task") String task,
			@FormDataParam("description") String description,
			@FormDataParam("step") String step,
			@FormDataParam("blueprintStep") String blueprintStep,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		Blueprint blueprint = null;
		List<Tasks> taskList = new ArrayList<Tasks>();
		Tasks tasks = new Tasks();
		try {
			blueprint = blueprintService.find(blueprintId);
			tasks.setTask(task);
			tasks.setDescription(description);
			tasks.setIsDeleted("0");
			tasks.setStep(step);
			tasks.setBlueprintStep(blueprintStep);	
			tasks.setX_axis(x_axis);
			tasks.setY_axis(y_axis);
			
			taskList.add(tasks);
			
			blueprint.setTasks(taskList);
			response.setHeader("Cache-Control", "no-cache");

			super.update(blueprintId, blueprint);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, blueprint);
	}

	@RequestMapping(value = "/updateBlueprint", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("blueprint") String blueprint,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		Blueprint entity = null;
		try {
			entity = blueprintService.find(blueprintId);
			entity.setBlueprint(blueprint);
			entity.setIsDeleted("0");

			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, entity);
	}

	@RequestMapping(value = "/getBlueprintObject", method = RequestMethod.GET)
	public @ResponseBody BlueprintMessage findBlueprint(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Blueprint blueprint = null;
		try {
			blueprint = blueprintService.find(blueprintId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, blueprint);
	}

	@RequestMapping(value = "/getBlueprintById", method = RequestMethod.GET)
	public @ResponseBody BlueprintsMessage find(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = blueprintService.getBlueprintById(blueprintId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintsMessage(status, entity);
	}

	@RequestMapping(value = "/deleteBlueprint", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long blueprintId = Long.parseLong(id);
		Blueprint templateBlueprint = null;
		try {
			templateBlueprint = blueprintService.find(blueprintId);
			templateBlueprint.setIsDeleted("1");
			setHeaders(response);
			super.create(templateBlueprint);
			super.delete(blueprintId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, templateBlueprint);
	}
	
	//Create relation b/w blueprint to scTaxonomy 
	@RequestMapping(value = "/createScTaxonomyRelation", method = RequestMethod.POST)
	public @ResponseBody BlueprintMessage scTaxonomyRelation(final Model model,
			@FormDataParam("ScTaxonomyId") String ScTaxonomyId,
			@FormDataParam("blueprintId") String blueprintId,			
			@FormDataParam("blueprintName") String blueprintName,	
			@FormDataParam("step") String step,			
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Long bpId = Long.parseLong(blueprintId);
		Long taxonomyId = Long.parseLong(ScTaxonomyId);	
		ScTaxonomy scTaxonomy = scTaxonomyService.find(taxonomyId);
		Blueprint blueprint = blueprintService.find(bpId);

		try{ 			
				CatalogueBlueprint catalogueBP =new CatalogueBlueprint();		
		 	 
        	  	catalogueBP = new CatalogueBlueprint();
        	  	catalogueBP.setBlueprintRelation(blueprint);
        	  	catalogueBP.setScTaxonomyRelation(scTaxonomy);
        	  	
        	  	catalogueBP.setBlueprintId(bpId);
        	  	catalogueBP.setTaxonomyId(taxonomyId);
        	  	catalogueBP.setBlueprintName(blueprintName);
  				catalogueBP.setStep(step);			
  				catalogueBP.setX_axis(x_axis);
  				catalogueBP.setY_axis(y_axis);
  				catalogueBP.setIsDeleted("0");	
  				
  				blueprint.addRelations(catalogueBP);	      
          	
		super.create(blueprint);
		
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BlueprintMessage(status, blueprint);
	}
	
	//Yaml generation
	@RequestMapping(value = "/generateyaml", method = RequestMethod.GET)
	 public @ResponseBody BlueprintsMessage generateyaml(@FormDataParam("id") String id) {
	  ResponseStatus status = null;
	  Long blueprintId = Long.parseLong(id);	
	  Iterable<Map<String, Object>> entity = null;
	  
	  try {
		  yamlGenerator.generateYaml(blueprintId);
		 // entity= blueprintService.generateYamlFile(blueprintId);
	   status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  
	  return new BlueprintsMessage(status, entity);
	 }
	
	@Override
	public MainService<Blueprint> getService() {
		return blueprintService;
	}

	public JSONObject getJSONObject(InputStream is) throws Exception {
		String result = getStringFromInputStream(is);
		System.out.println("JSONObject Result :" + result);
		return (JSONObject) new JSONParser().parse(result);
	}

	public JSONArray getJSONArray(InputStream is) throws Exception {
		String result = getStringFromInputStream(is);
		System.out.println("JSONArray Result :" + result);
		return (JSONArray) new JSONParser().parse(result);
	}

	/**
	 * convert InputStream to String
	 *
	 * @param is
	 * @return String
	 */
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return sb.toString();
	}
}
