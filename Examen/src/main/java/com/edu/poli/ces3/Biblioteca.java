package com.edu.poli.ces3;



import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import com.edu.poli.ces3.models.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.util.*;

public class Biblioteca {
    private Document doc;

    public Biblioteca(String xmlPath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(xmlPath);
            this.doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarLibrosPorPrecio(double precio) {
        NodeList libros = doc.getElementsByTagName("libro");
        for (int i = 0; i < libros.getLength(); i++) {
            Element libro = (Element) libros.item(i);
            double libroPrecio = Double.parseDouble(libro.getElementsByTagName("precio").item(0).getTextContent());
            if (libroPrecio == precio) {
                String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
                System.out.println("Libro encontrado: " + titulo);
            }
        }
    }

    public void listarAutores() {
        NodeList autores = doc.getElementsByTagName("autor");
        Set<String> autoresSet = new HashSet<>();
        for (int i = 0; i < autores.getLength(); i++) {
            Element autor = (Element) autores.item(i);
            String nombre = autor.getElementsByTagName("nombre").item(0).getTextContent();
            String apellido = autor.getElementsByTagName("apellido").item(0).getTextContent();
            autoresSet.add(nombre + " " + apellido);
        }
        System.out.println("Lista de autores:");
        for (String autor : autoresSet) {
            System.out.println("- " + autor);
        }
    }

    public void listarLibros() {
        NodeList libros = doc.getElementsByTagName("libro");
        List<Libro> listaLibros = new ArrayList<>();
        for (int i = 0; i < libros.getLength(); i++) {
            Element libro = (Element) libros.item(i);
            String titulo = libro.getElementsByTagName("titulo").item(0).getTextContent();
            int anyo = Integer.parseInt(libro.getAttribute("anyo"));
            String autor = "";
            NodeList autores = libro.getElementsByTagName("autor");
            for (int j = 0; j < autores.getLength(); j++) {
                Element autorElement = (Element) autores.item(j);
                String nombre = autorElement.getElementsByTagName("nombre").item(0).getTextContent();
                String apellido = autorElement.getElementsByTagName("apellido").item(0).getTextContent();
                if (j == 0) {
                    autor += nombre + " " + apellido;
                } else {
                    autor += ", " + nombre + " " + apellido;
                }
            }
            listaLibros.add(new Libro(titulo, anyo, autor));
        }
        Collections.sort(listaLibros, new Comparator<Libro>() {
            @Override
            public int compare(Libro l1, Libro l2) {
                int tituloCompare = l1.getTitulo().compareTo(l2.getTitulo());
                if (tituloCompare != 0) {
                    return tituloCompare;
                }
                int anyoCompare = Integer.compare(l1.getAnyo(), l2.getAnyo());
                if (anyoCompare != 0) {
                    return anyoCompare;
                }
                return l1.getAutor().compareTo(l2.getAutor());
            }
        });

        System.out.println("Lista de libros:");
        for (Libro libro : listaLibros) {
            System.out.println("- " + libro.getTitulo() + " (" + libro.getAnyo() + ") - " + libro.getAutor());
        }
    }
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca("src\\main\\resources\\Examen.xml");
        biblioteca.buscarLibrosPorPrecio(65.95);
        biblioteca.listarAutores();
        biblioteca.listarLibros();
    }
}
