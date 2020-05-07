package ss19.ue06.db;

import java.util.*;

public class Animal implements Comparable<Animal> {

	private Person owner;
	private final int id;
	private final String name;
	private final Animal father;
	private final Animal mother;
	private final Map<Integer, Animal> children;

	Animal(Person owner, int id, String name, Animal father, Animal mother) {
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.father = father;
		this.mother = mother;
		this.children = new HashMap<Integer, Animal>();
	}

	@Override
	public int compareTo(Animal a) {
		return this.getId() - a.getId();
	}

	@Override
	public String toString() {
		return ("(" + this.getId() + ")" + this.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Animal other = (Animal) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Person getOwner() {
		return owner;
	}

	void setOwner(Person owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Animal getFather() {
		return this.father;
	}

	public Animal getMother() {
		return this.mother;
	}

	void addChild(Animal child) {
		this.children.put(child.getId(), child);
	}

	public SortedSet<Animal> getChildren() {
		SortedSet<Animal> children = new TreeSet<Animal>();
		this.children.forEach((id, animal) -> children.add(animal));
		return children;
	}

	public SortedSet<Animal> getAncestors() {
		SortedSet<Animal> ancestors = new TreeSet<Animal>();
		if (this.getFather() != null) {
			ancestors.add(this.getFather());
			ancestors.addAll(this.getFather().getAncestors());
		}
		if (this.getMother() != null) {
			ancestors.add(this.getMother());
			ancestors.addAll(this.getMother().getAncestors());
		}
		return ancestors;
	}

	public SortedSet<Animal> getDescendants() {
		SortedSet<Animal> descendants = new TreeSet<Animal>();
		this.children.forEach((id, animal) -> {
			descendants.add(animal);
			descendants.addAll(animal.getDescendants());
		});
		return descendants;
	}

	public int getNumberOfAncestors() {
		return this.getAncestors().size();
	}

	public int getNumberOfDescendants() {
		return this.getDescendants().size();
	}
}