package model.data;

import model.JSONable;

/**
 * General class to unify
 * 
 * @author Wm. Keith van der Meulen
 *
 */
public abstract class EntityID implements JSONable {
	
	protected String id;
	
	public EntityID(String id) {
		super();
		this.id = id;
	}
	
	public EntityID() {
		
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getID();
	}

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
		EntityID other = (EntityID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
