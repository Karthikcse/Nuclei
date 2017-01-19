/**
 * 
 */
package nuclei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.CatalogueBlueprint;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.response.CatalogueBlueprintMessage;
import nuclei.response.CatalogueBlueprintsMessage;
import nuclei.service.CatalogueBlueprintService;
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
public class CatalogueBlueprintController extends
		MainController<CatalogueBlueprint> {

	@Autowired
	private CatalogueBlueprintService catalogueBlueprintService;

	@RequestMapping(value = "/catalogueBlueprints", method = RequestMethod.GET)
	public @ResponseBody CatalogueBlueprintsMessage list(
			final HttpServletResponse response) {

		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = catalogueBlueprintService.getCatalogueBlueprint();
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
		return new CatalogueBlueprintsMessage(status, entity);
	}

	@RequestMapping(value = "/insertCatalogueBlueprint", method = RequestMethod.POST)
	public @ResponseBody CatalogueBlueprintMessage create(
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
		CatalogueBlueprint entity = new CatalogueBlueprint();
		try {
			entity.setBlueprintId(bpId);
			entity.setTaxonomyId(taxonomyId);
			entity.setBlueprintName(blueprintName);
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
		return new CatalogueBlueprintMessage(status, entity);
	}

	@RequestMapping(value = "/updateCatalogueBlueprint", method = RequestMethod.POST)
	public @ResponseBody CatalogueBlueprintMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("ScTaxonomyId") String ScTaxonomyId,
			@FormDataParam("blueprintId") String blueprintId,
			@FormDataParam("blueprintName") String blueprintName,
			@FormDataParam("step") String step,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {
		ResponseStatus status = null;
		Long catalogueBlueprintId = Long.parseLong(id);
		Long bpId = Long.parseLong(blueprintId);
		Long taxonomyId = Long.parseLong(ScTaxonomyId);
		CatalogueBlueprint entity = null;
		try {

			entity = catalogueBlueprintService.find(catalogueBlueprintId);

			entity.setBlueprintId(bpId);
			entity.setTaxonomyId(taxonomyId);
			entity.setBlueprintName(blueprintName);
			entity.setStep(step);
			entity.setX_axis(x_axis);
			entity.setY_axis(y_axis);
			entity.setIsDeleted("0");

			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintMessage(status, entity);
	}

	@RequestMapping(value = "/getCatalogueBlueprintById", method = RequestMethod.GET)
	public @ResponseBody CatalogueBlueprintsMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long catalogueBlueprintId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = catalogueBlueprintService.getCatalogueBlueprintById(catalogueBlueprintId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintsMessage(status, entity);
	}

	@RequestMapping(value = "/deleteCatalogueBlueprintById", method = RequestMethod.POST)
	public @ResponseBody CatalogueBlueprintsMessage deleteCatalogueBP(
			@FormDataParam("blueprintId") String blueprintId, @FormDataParam("taxonomyId") String taxonomyId,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long BpId = Long.parseLong(blueprintId);
		Long taxId = Long.parseLong(taxonomyId);
		Iterable<Map<String, Object>> blueprintFunction = null;
		try {
			blueprintFunction = catalogueBlueprintService.DeleteRelationship(BpId,taxId);
			response.setHeader("Cache-Control", "no-cache");	
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintsMessage(status, blueprintFunction);
	}

	@RequestMapping(value = "/getCatalogueBlueprintRelationById", method = RequestMethod.GET)
	public @ResponseBody CatalogueBlueprintsMessage findRelationById(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long catalogueBlueprintId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = catalogueBlueprintService.getRelation(catalogueBlueprintId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintsMessage(status, entity);
	}

	// Update step value
	@RequestMapping(value = "/updateRelationStep", method = RequestMethod.POST)
	public @ResponseBody CatalogueBlueprintsMessage deleteTest(
			@FormDataParam("blueprintId") long blueprintId,
			@FormDataParam("taxonomyId") long taxonomyId,
			@FormDataParam("step") String step,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = catalogueBlueprintService.updateStep(blueprintId,taxonomyId, step);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintsMessage(status, entity);
	}

	// Update x-axis & y-axis
	@RequestMapping(value = "/updateCatalogueBlueprintPosition", method = RequestMethod.POST)
	public @ResponseBody CatalogueBlueprintsMessage updateFunctionPosition(
			@FormDataParam("blueprintId") long blueprintId,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response, Model model) {

		ResponseStatus status = null;
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = catalogueBlueprintService.updatePosition(blueprintId,x_axis, y_axis);
			System.out.println("Entity : -->"+entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CatalogueBlueprintsMessage(status, entity);
	}
	
	@Override
	public MainService<CatalogueBlueprint> getService() {
		return catalogueBlueprintService;
	}
}
