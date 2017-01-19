package nuclei.service;

public interface MainService<T> {

	Iterable<T> findAll();

	T find(Long id);

	void delete(Long id);

	T createOrUpdate(T object);

}
