package ss19.ue06.db;

import java.util.*;

public class Database {

	private final Map<String, Person> persons;
	private final Map<Integer, Animal> animals;

	public Database() {
		this.persons = new HashMap<String, Person>();
		this.animals = new HashMap<Integer, Animal>();
	}

	public void addPerson(String name) {
		Person newPerson = new Person(name);
		this.persons.put(name, newPerson);
	}

	public void addAnimal(Person owner, int id, String name, Animal father, Animal mother) {
		Animal newAnimal = new Animal(owner, id, name, father, mother);
		this.animals.put(id, newAnimal);
		owner.add(newAnimal);
		if (newAnimal.getFather() != null) {
			newAnimal.getFather().addChild(newAnimal);
		}
		if (newAnimal.getMother() != null) {
			newAnimal.getMother().addChild(newAnimal);
		}
	}

	public void tradeAnimal(Animal animal, Person newOwner) {
		animal.getOwner().remove(animal);
		animal.setOwner(newOwner);
		newOwner.add(animal);
	}

	public Person getPerson(String name) {
		return this.persons.get(name);
	}

	public Animal getAnimal(int id) {
		return this.animals.get(id);
	}

	public SortedSet<Person> getPersons() {
		SortedSet<Person> persons = new TreeSet<Person>();
		this.persons.forEach((id, person) -> persons.add(person));
		return persons;
	}

	public SortedSet<Animal> getAnimals() {
		SortedSet<Animal> animals = new TreeSet<Animal>();
		this.animals.forEach((id, animal) -> animals.add(animal));
		return animals;
	}

	public SortedSet<Person> getPersons(Comparator<Person> comparator) {
		TreeSet<Person> persons = new TreeSet<Person>(comparator);
		persons.addAll(this.getPersons());
		return persons;
	}

	public SortedSet<Animal> getAnimals(Comparator<Animal> comparator) {
		TreeSet<Animal> animals = new TreeSet<Animal>(comparator);
		animals.addAll(this.getAnimals());
		return animals;
	}
}