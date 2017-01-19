/**
 * 
 */
package nuclei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.ScTaxonomy;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.ScTaxonomyMessage;
import nuclei.response.ScTaxonomysMessage;
import nuclei.service.CatalogueBlueprintService;
import nuclei.service.ScTaxonomyService;
import nuclei.service.MainService;

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
public class ScTaxonomyController extends MainController<ScTaxonomy> {

	@Autowired
	private ScTaxonomyService scTaxonomyService;

	@Autowired
	private CatalogueBlueprintService catalogueBlueprintService;

	@RequestMapping(value = "/scTaxonomys", method = RequestMethod.GET)
	public @ResponseBody ScTaxonomysMessage list(final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = scTaxonomyService.getScTaxonomyValues();
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
		return new ScTaxonomysMessage(status, entity);

	}

	@RequestMapping(value = "/insertScTaxonomy", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage create(@FormDataParam("taxName") String taxName,
			@FormDataParam("description") String description,	
			@FormDataParam("step") String step,
			@FormDataParam("serviceCatalogueStep") String serviceCatalogueStep,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		ScTaxonomy entity = new ScTaxonomy();
		try {
			entity.setTaxName(taxName);
			entity.setDescription(description);	
			entity.setServiceCatalogueStep(serviceCatalogueStep);
			entity.setStep(step);
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, entity);
	}

	@RequestMapping(value = "/updateScStep", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage updateStep(@FormDataParam("id") String id,
			@FormDataParam("step") String step,
			@FormDataParam("serviceCatalogueStep") String serviceCatalogueStep,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		ScTaxonomy entity = null;
		ScTaxonomy newEntity = null;
		try {
			entity = scTaxonomyService.find(taxonomyId);
			newEntity = scTaxonomyService.find(taxonomyId);			
			newEntity.setTaxName(entity.getTaxName());
			newEntity.setDescription(entity.getDescription());			
			newEntity.setX_axis(entity.getX_axis());
			newEntity.setY_axis(entity.getY_axis());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(step);
			newEntity.setServiceCatalogueStep(serviceCatalogueStep);
			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, newEntity);
	}
	
	@RequestMapping(value = "/updateScTaxonomyStep", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage updateScTaxonomyStep(@FormDataParam("id") String id,
			@FormDataParam("serviceCatalogueStep") String serviceCatalogueStep,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		ScTaxonomy entity = null;
		ScTaxonomy newEntity = null;
		try {
			entity = scTaxonomyService.find(taxonomyId);
			newEntity = scTaxonomyService.find(taxonomyId);			
			newEntity.setTaxName(entity.getTaxName());
			newEntity.setDescription(entity.getDescription());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(entity.getStep());
			newEntity.setX_axis(entity.getX_axis());
			newEntity.setY_axis(entity.getY_axis());
			newEntity.setServiceCatalogueStep(serviceCatalogueStep);			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, newEntity);
	}

	//Update x-axis & y-axis
	@RequestMapping(value = "/updateScTaxonomyPosition", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage updatePosition(@FormDataParam("id") String id,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		ScTaxonomy entity = null;
		ScTaxonomy newEntity = null;
		try {
			entity = scTaxonomyService.find(taxonomyId);
			newEntity = scTaxonomyService.find(taxonomyId);
			newEntity.setTaxName(entity.getTaxName());
			newEntity.setDescription(entity.getDescription());
			newEntity.setIsDeleted(entity.getIsDeleted());
			newEntity.setStep(entity.getStep());
			newEntity.setX_axis(x_axis);
			newEntity.setY_axis(y_axis);
			newEntity.setServiceCatalogueStep(entity.getServiceCatalogueStep());			
			super.create(newEntity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, newEntity);
	}
	
	@RequestMapping(value = "/updateScTaxonomy", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage update(@FormDataParam("id") String id,
			@FormDataParam("taxName") String taxName,
			@FormDataParam("description") String description,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		ScTaxonomy entity = null;
		try {
			entity = scTaxonomyService.find(taxonomyId);
			entity.setTaxName(taxName);
			entity.setDescription(description);
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, entity);
	}

	@RequestMapping(value = "/getScTaxonomyById", method = RequestMethod.GET)
	public @ResponseBody ScTaxonomysMessage find(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = scTaxonomyService.getScTaxonomyById(taxonomyId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomysMessage(status, entity);
	}

	@RequestMapping(value = "/deleteScTaxonomy", method = RequestMethod.POST)
	public @ResponseBody ScTaxonomyMessage delete(@FormDataParam("id") String id,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long taxonomyId = Long.parseLong(id);
		ScTaxonomy taxonomy = null;
		try {
			taxonomy = scTaxonomyService.find(taxonomyId);
			taxonomy.setIsDeleted("1");
			setHeaders(response);
			super.create(taxonomy);
			super.delete(taxonomyId);				
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ScTaxonomyMessage(status, taxonomy);
	}	
		
	@Override
	public MainService<ScTaxonomy> getService() {
		return scTaxonomyService;
	}
}
