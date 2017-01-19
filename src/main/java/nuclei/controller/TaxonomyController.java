/**
 * 
 */
package nuclei.controller;

import javax.servlet.http.HttpServletResponse;

import nuclei.domain.Taxonomy;
import nuclei.service.TaxonomyService;
import nuclei.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.multipart.FormDataParam;

/**
 * @author Karthikeyan
 *
 */

@RestController
// @Controller
public class TaxonomyController extends MainController<Taxonomy> {

	@Autowired
	private TaxonomyService taxonomyService;

	@RequestMapping(value = "/taxonomys", method = RequestMethod.GET)
	public @ResponseBody Iterable<Taxonomy> list(
			final HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		return super.list();
	}

	@RequestMapping(value = "/taxonomy", method = RequestMethod.POST)
	public Taxonomy create(@FormDataParam("name") String name,
			@FormDataParam("classType") String classType,
			@FormDataParam("version") String version,
			final HttpServletResponse response) {

		Taxonomy entity = new Taxonomy();
		entity.setName(name);
		entity.setClassType(classType);
		entity.setVersion(version);
		response.setHeader("Cache-Control", "no-cache");
		super.create(entity);
		return entity;
	}

	@RequestMapping(value = "/taxonomy/{id}", method = RequestMethod.GET)
	public Taxonomy find(@PathVariable Long id,
			final HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
		return super.find(id);
	}

	@RequestMapping(value = "/taxonomy/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id, final HttpServletResponse response) {
		setHeaders(response);
		super.delete(id);
	}

	@RequestMapping(value = "/taxonomy/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public Taxonomy update(@PathVariable Long id, @RequestBody Taxonomy entity,
			final HttpServletResponse response) {
		setHeaders(response);
		return super.update(id, entity);
	}

	@Override
	public MainService<Taxonomy> getService() {
		return taxonomyService;
	}

}
