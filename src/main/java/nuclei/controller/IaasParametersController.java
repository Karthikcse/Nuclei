/**
 * 
 */
package nuclei.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.IaasParameters;
import nuclei.response.IaasParameterMessage;
import nuclei.response.IaasParametersMessage;
import nuclei.response.ResponseStatus;
import nuclei.response.ResponseStatusCode;
import nuclei.service.IaasParametersService;
import nuclei.service.MainService;

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
public class IaasParametersController extends MainController<IaasParameters> {

	@Autowired
	private IaasParametersService iaasParametersService;

	@RequestMapping(value = "/iaasParameters", method = RequestMethod.GET)
	public @ResponseBody IaasParametersMessage list(
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Iterable<Map<String, Object>> entity = null;
		response.setHeader("Cache-Control", "no-cache");
		try {
			entity = iaasParametersService.getIaasParameters();
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
		return new IaasParametersMessage(status, entity);
	}

	@RequestMapping(value = "/createIaasParameters", method = RequestMethod.POST)
	public @ResponseBody IaasParameterMessage create(
			@FormDataParam("iaasConfig") String iaasConfig,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		IaasParameters entity = new IaasParameters();
		try {
			entity.setIaasConfig(iaasConfig);
			entity.setIsDeleted("0");
			response.setHeader("Cache-Control", "no-cache");
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasParameterMessage(status, entity);
	}

	@RequestMapping(value = "/updateIaasParameters", method = RequestMethod.POST)
	public @ResponseBody IaasParameterMessage update(
			@FormDataParam("id") String id, 	
			@FormDataParam("iaasConfig") String iaasConfig,
			final HttpServletResponse response) {
		ResponseStatus status = null;
		Long paramId = Long.parseLong(id);
		IaasParameters entity = null;
		try {
			entity = iaasParametersService.find(paramId);
			entity.setIaasConfig(iaasConfig);
			entity.setIsDeleted("0");
			setHeaders(response);
			super.create(entity);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasParameterMessage(status, entity);
	}

	@RequestMapping(value = "/getIaasParametersById", method = RequestMethod.GET)
	public @ResponseBody IaasParametersMessage find(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long paramId = Long.parseLong(id);
		response.setHeader("Cache-Control", "no-cache");
		Iterable<Map<String, Object>> entity = null;
		try {
			entity = iaasParametersService.getIaasParameterById(paramId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasParametersMessage(status, entity);
	}

	@RequestMapping(value = "/deleteIaasParameters", method = RequestMethod.POST)
	public @ResponseBody IaasParameterMessage delete(
			@FormDataParam("id") String id, final HttpServletResponse response) {
		ResponseStatus status = null;
		Long paramId = Long.parseLong(id);
		IaasParameters iaasParameters = null;
		try {
			iaasParameters = iaasParametersService.find(paramId);
			iaasParameters.setIsDeleted("1");
			setHeaders(response);
			super.create(iaasParameters);
			super.delete(paramId);
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK, "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new IaasParameterMessage(status, iaasParameters);
	}

	@Override
	public MainService<IaasParameters> getService() {
		return iaasParametersService;
	}

}
