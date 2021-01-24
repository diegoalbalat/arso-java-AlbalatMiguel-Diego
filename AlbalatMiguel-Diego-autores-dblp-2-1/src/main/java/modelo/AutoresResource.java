package modelo;

public class AutoresResource {
	protected Links _links;
	protected Embedded _embedded;
	protected int count;
	protected int total;

	public AutoresResource() {

	}

	public AutoresResource(Links _links, Embedded _embedded, int count, int total) {
		super();
		this._links = _links;
		this._embedded = _embedded;
		this.count = count;
		this.total = total;
	}

	public Links get_links() {
		return _links;
	}

	public void set_links(Links _links) {
		this._links = _links;
	}

	public Embedded get_embedded() {
		return _embedded;
	}

	public void set_embedded(Embedded _embedded) {
		this._embedded = _embedded;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
