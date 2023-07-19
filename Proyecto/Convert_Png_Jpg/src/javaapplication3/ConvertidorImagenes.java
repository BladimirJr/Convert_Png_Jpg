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
        // Directorio de las imágenes PNG
        String directorioImagenes = "C:\\Users\\SyD Colombia SA\\Desktop\\png";

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
                    BufferedImage imagenRedimensionada = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2d = imagenRedimensionada.createGraphics();
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(0, 0, 300, 300);
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2d.drawImage(imagen, 0, 0, 300, 300, null);
                    g2d.dispose();

                    // Obtener el nombre del archivo sin la extensión
                    String nombreArchivo = archivo.getName().replaceFirst("[.][^.]+$", "");
                    
                    nombreArchivo = nombreArchivo.replace("-1", "");

                    // Guardar la imagen convertida en formato JPEG
                    String rutaSalida = directorioSalida + File.separator + nombreArchivo + ".jpg";
                    ImageIO.write(imagenRedimensionada, "jpg", new File(rutaSalida));

                    System.out.println("yes");
                } catch (IOException e) {
                }
            }
        }

        System.out.println("Proceso completado.");
    }
}

// Vladimir Estrada.
