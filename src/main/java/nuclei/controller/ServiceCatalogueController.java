/**
 * 
 */
package nuclei.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.ScTaxonomy;
import nuclei.domain.ServiceCatalogue;
import nuclei.response.ServiceCatalogueMessage;
import nuclei.response.ServiceCataloguesMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.service.MainService;
import nuclei.service.ServiceCatalogueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ServiceCatalogueController extends
		MainController<ServiceCatalogue> {

	@Autowired
	private ServiceCatalogueService serviceCatalogueService;

	@RequestMapping(value = "/catalogues", method = RequestMethod.GET)
	public @ResponseBody ServiceCataloguesMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = serviceCatalogueService.getServiceCatalogues();
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
		return new ServiceCataloguesMessage(status, entity);
	}

	@RequestMapping(value = "/insertCatalogue", method = RequestMethod.POST)
	public @ResponseBody ServiceCatalogueMessage create(
			@FormDataParam("scName") String scName,
			@FormDataParam("shortDesc") String shortDesc,
			@FormDataParam("description") String description,
			final HttpServletResponse response) {

		ResponseStatus status = null;
		ServiceCatalogue entity = new ServiceCatalogue();
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity.setScName(scName);
			entity.setShortDesc(shortDesc);
			entity.setDescription(description);
			entity.setIsDeleted("0");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCatalogueMessage(status, entity);
	}

	@RequestMapping(value = "/addScTaxonomyByCatalogueId", method = RequestMethod.POST)
	public @ResponseBody ServiceCatalogueMessage addTasks(
			@FormDataParam("id") String id,
			@FormDataParam("taxName") String taxName,
			@FormDataParam("description") String description,
			@FormDataParam("step") String step,
			@FormDataParam("serviceCatalogueStep") String serviceCatalogueStep,
			@FormDataParam("x_axis") String x_axis,
			@FormDataParam("y_axis") String y_axis,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long scId = Long.parseLong(id);
		ServiceCatalogue catalogue = null;
		List<ScTaxonomy> taxonomyList = new ArrayList<ScTaxonomy>();
		ScTaxonomy taxonomy = new ScTaxonomy();
		try {
			catalogue = serviceCatalogueService.find(scId);
			taxonomy.setTaxName(taxName);
			taxonomy.setDescription(description);
			taxonomy.setStep(step);
			taxonomy.setServiceCatalogueStep(serviceCatalogueStep);
			taxonomy.setX_axis(x_axis);
			taxonomy.setY_axis(y_axis);
			taxonomy.setIsDeleted("0");

			taxonomyList.add(taxonomy);

			catalogue.setScTaxonomy(taxonomyList);
			response.setHeader("Cache-Control", "no-cache");

			super.update(scId, catalogue);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCatalogueMessage(status, catalogue);
	}

	@RequestMapping(value = "/updateCatalogue", method = RequestMethod.POST)
	public @ResponseBody ServiceCatalogueMessage update(
			@FormDataParam("id") String id,
			@FormDataParam("scName") String scName,
			@FormDataParam("shortDesc") String shortDesc,
			@FormDataParam("description") String description,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long scId = Long.parseLong(id);
		ServiceCatalogue entity = null;
		try {
			entity = serviceCatalogueService.find(scId);
			entity.setScName(scName);
			entity.setShortDesc(shortDesc);
			entity.setDescription(description);
			entity.setIsDeleted("0");

			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCatalogueMessage(status, entity);
	}

	@RequestMapping(value = "/getCatalogueObject", method = RequestMethod.GET)
	public @ResponseBody ServiceCatalogueMessage findBlueprint(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long scId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		ServiceCatalogue catalogue = null;
		try {
			catalogue = serviceCatalogueService.find(scId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCatalogueMessage(status, catalogue);
	}

	@RequestMapping(value = "/getCatalogueById", method = RequestMethod.GET)
	public @ResponseBody ServiceCataloguesMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long scId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = serviceCatalogueService.getServiceCatalogueById(scId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCataloguesMessage(status, entity);
	}

	@RequestMapping(value = "/deleteCatalogue", method = RequestMethod.POST)
	public @ResponseBody ServiceCatalogueMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long scId = Long.parseLong(id);
		ServiceCatalogue catalogue = null;
		try {
			catalogue = serviceCatalogueService.find(scId);
			catalogue.setIsDeleted("1");
			setHeaders(response);
			super.create(catalogue);
			super.delete(scId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceCatalogueMessage(status, catalogue);
	}

	@Override
	public MainService<ServiceCatalogue> getService() {
		return serviceCatalogueService;
	}
}
