package zara.org.Repository;

import java.io.*;

public class DbContext {
    private final String FILENAME;

    public DbContext(String filename) {
        FILENAME = filename;
        createFileIfNew();

    }

    public DbSet GetDatabase() {
        DbSet database = new DbSet();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            database = (DbSet) objectInputStream.readObject();

        } catch (EOFException e) {

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return database;
    }

    public void SaveChanges(DbSet database) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFileIfNew() {
        File file = new File(FILENAME);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " +file.getAbsoluteFile());
                } else {
                    System.out.println("Failed to create file: " +file.getAbsoluteFile());
                }
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " +e.getMessage());
        }
    }
}
