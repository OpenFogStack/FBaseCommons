package model;

import model.data.EntityID;

/**
 * 
 * Base class for all model classes that require JSON serialization.
 * 
 * @author jonathanhasenburg
 *
 */
public abstract class Entity implements JSONable {

	protected EntityID id = null;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// TODO add utility methods to split host:port into parts (and to create a string using both)

}
