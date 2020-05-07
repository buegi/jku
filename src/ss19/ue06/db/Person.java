package ss19.ue06.db;

import java.util.*;

public class Person implements Comparable<Person> {

	private final String name;
	private final Map<Integer, Animal> animals;

	Person(String name) {
		this.name = name;
		this.animals = new HashMap<Integer, Animal>();
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Person o) {
		return this.getName().compareToIgnoreCase(o.getName());
	}

	void add(Animal animal) {
		this.animals.put(animal.getId(), animal);
	}

	void remove(Animal animal) {
		this.animals.remove(animal.getId());
	}

	public SortedSet<Animal> getAnimals() {
		SortedSet<Animal> animals = new TreeSet<Animal>();
		this.animals.forEach((id, animal) -> animals.add(animal));
		return animals;
	}

	public SortedSet<Animal> getAnimalsSortedByName() {
		SortedSet<Animal> animals = new TreeSet<Animal>((a1, a2) -> a1.getName().compareToIgnoreCase(a2.getName()));
		animals.addAll(this.getAnimals());
		return animals;
	}

	public SortedSet<Animal> getAnimalsSortedByAncestorCount() {
		SortedSet<Animal> animals = new TreeSet<Animal>(
				(a1, a2) -> Integer.compare(a1.getAncestors().size(), a2.getAncestors().size()));
		animals.addAll(this.getAnimals());
		return animals;
	}

	public SortedSet<Animal> getAnimalsSortedByDescendantCount() {
		SortedSet<Animal> animals = new TreeSet<Animal>(
				(a1, a2) -> Integer.compare(a1.getDescendants().size(), a2.getDescendants().size()));
		animals.addAll(this.getAnimals());
		return animals;
	}
}