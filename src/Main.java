abstract class LibraryItem implements Transportable, Ownable{
    String title;
    String text;
    String library;
    String owner;


    public void read() {
        System.out.println("Читаем:");
        System.out.println(title);
        System.out.println(text);
    }


    public String getCurrentLibrary() {
        return library;
    }

    public void moveToNewLibrary(String libraryName) {
        library = libraryName;
    }

    public void takeFromLibrary(String newOwnerName) {
        owner = newOwnerName;
    }

    public void returnToLibrary() {
        owner = "";
    }


}

interface Transportable{
    String getCurrentLibrary();
    void moveToNewLibrary(String libraryName);



}

interface Ownable{
    void takeFromLibrary(String newOwnerName);
    void returnToLibrary();

}


class Book extends LibraryItem {

}

class Journal extends LibraryItem {

}

class EBook extends LibraryItem {

}