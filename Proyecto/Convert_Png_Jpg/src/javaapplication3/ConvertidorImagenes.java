/*
Vladimir Estrada.
 */
package javaapplication3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ConvertidorImagenes {
    public static void main(String[] args) {
        final int IMAGE_WIDTH = 300;
        final int IMAGE_HEIGHT = 300;
        final String EXTENSION = "jpg";

        // Directorio de las imágenes PNG
        String directorioImagenes = "C:\\Users\\SyD Colombia SA\\Desktop\\z";

        // Directorio de salida para las imágenes convertidas
        String directorioSalida = "C:\\Users\\SyD Colombia SA\\Desktop\\n";

        // Crear el directorio de salida si no existe
        File directorioSalidaFile = new File(directorioSalida);
        if (!directorioSalidaFile.exists()) {
            directorioSalidaFile.mkdirs();
        }

        // Obtener la lista de archivos en el directorio de imágenes
        File directorioImagenesFile = new File(directorioImagenes);
        File[] archivos = directorioImagenesFile.listFiles();

        // Recorrer cada archivo en el directorio
        for (File archivo : archivos) {
            // Verificar si el archivo es una imagen PNG
            if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".png")) {
                try {
                    // Leer la imagen PNG
                    BufferedImage imagen = ImageIO.read(archivo);

                    // Crear una nueva imagen con fondo blanco
                    BufferedImage imagenRedimensionada = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = imagenRedimensionada.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2d.drawImage(imagen, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, null);
                    g2d.dispose();

                    // Obtener el nombre del archivo sin la extensión
                    String nombreArchivo = archivo.getName().replaceFirst("[.][^.]+$", "");
                    nombreArchivo = nombreArchivo.replace("-1", "");

                    // Guardar la imagen convertida en formato JPEG
                    String rutaSalida = directorioSalida + File.separator + nombreArchivo + "." + EXTENSION;
                    ImageIO.write(imagenRedimensionada, EXTENSION, new File(rutaSalida));
                } catch (IOException e) {
                    System.err.println("Error al procesar el archivo: " + archivo.getName());
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Proceso completado.");
    }
}

// Vladimir Estrada..
