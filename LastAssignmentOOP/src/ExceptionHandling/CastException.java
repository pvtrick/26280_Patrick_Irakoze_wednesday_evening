package ExceptionHandling;

public class CastException {

	 public static void main(String[] args) {
	        // Parent class
	        class Animal {
	            void sound() {
	                System.out.println("This is an animal.");
	            }
	        }

	        // Subclass
	        class Dog extends Animal {
	            @Override
	            void sound() {
	                System.out.println("Bark!");
	            }
	        }

	        // Another subclass
	        class Cat extends Animal {
	            @Override
	            void sound() {
	                System.out.println("Meow!");
	            }
	        }

	        // Valid cast: Parent reference to Child instance
	        Animal myAnimal = new Dog(); // A Dog is an Animal
	        myAnimal.sound();

	        try {
	            // Invalid cast: Trying to cast Dog instance to Cat
	            Cat myCat = (Cat) myAnimal; // This will throw ClassCastException
	            myCat.sound();
	        } catch (ClassCastException e) {
	            System.err.println("ClassCastException occurred: " + e.getMessage());
	        }
	    }
	}

