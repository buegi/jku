package swe2.ss19.ue10.demo.patterns.singleton;

public class SingletonMain {

	public static void main(String[] args) {		
		Singleton.getInstance().useSingleton();
		Singleton.getInstance().useSingleton();
		
		SingletonAsEnum.INSTANCE.useSingleton();
		SingletonAsEnum.INSTANCE.useSingleton();
	}

}
