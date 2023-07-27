/*
Vladimir Estrada.
 */
package javaapplication3;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConvertidorImagenes {
    public static void main(String[] args) {
        final int IMAGE_WIDTH = 300;
        final int IMAGE_HEIGHT = 300;
        final String EXTENSION_PNG = ".png";
        final String EXTENSION_JPG = ".jpg";

        // Directorio de las imágenes PNG
        String directorioImagenes = "C:\\Users\\SyD Colombia SA\\Desktop\\p";

        // Directorio de salida para las imágenes convertidas
        String directorioSalida = "C:\\Users\\SyD Colombia SA\\Desktop\\z";

        // Crear el directorio de salida si no existe
        File directorioSalidaFile = new File(directorioSalida);
        if (!directorioSalidaFile.exists()) {
            directorioSalidaFile.mkdirs();
        }

        try {
            // Cargar el archivo Excel
            String rutaExcel = "C:\\\\Users\\\\SyD Colombia SA\\\\Desktop\\\\Libro2.xlsx";
            try (FileInputStream fileInputStream = new FileInputStream(rutaExcel)) {
                Workbook workbook = new XSSFWorkbook(fileInputStream);
                Sheet sheet = workbook.getSheetAt(0);
                
                // Iterar por las filas del archivo Excel
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    // Suponiendo que los nombres de las imágenes están en la primera columna (A)
                    Cell cell = row.getCell(0); 
                    if (cell != null && cell.getCellTypeEnum() == CellType.NUMERIC ) {
                        // Corregí error, estaba tomando los valores como String y los valores eran de tipo numerico.
                        String nombreImagen = String.valueOf((int) cell.getNumericCellValue()).trim();
                        // Verificar si el archivo existe en la ruta de las imágenes con el nombre.
                        String nombreArchivoPNG = nombreImagen.endsWith(EXTENSION_PNG) ? nombreImagen : nombreImagen + EXTENSION_PNG;
                        System.out.println("Convertido a jpg: "+nombreArchivoPNG);
                        File archivo = new File(directorioImagenes, nombreArchivoPNG);
                        if (!archivo.exists()) {
                            // Si el archivo no existe con el nombre, buscarlo con el sufijo "-1"
                            String nombreArchivoPNG1 = nombreImagen.endsWith(EXTENSION_PNG) ? nombreImagen.replace(EXTENSION_PNG, "-1" + EXTENSION_PNG) : nombreImagen + "-1" + EXTENSION_PNG;
                            archivo = new File(directorioImagenes, nombreArchivoPNG1);
                        }
                        // Si el archivo no se encontró, omitir el procesamiento
                        if (!archivo.exists()) {
                            System.out.println("No se encontró el archivo: " + nombreImagen);
                            continue;
                        }
                        
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
                        
                        // Guardar la imagen convertida en formato JPEG
                        String nombreArchivoJPG = nombreImagen.endsWith(EXTENSION_PNG) ? nombreImagen.replace(EXTENSION_PNG, EXTENSION_JPG) : nombreImagen + EXTENSION_JPG;
                        String rutaSalida = directorioSalida + File.separator + nombreArchivoJPG;
                        ImageIO.write(imagenRedimensionada, "jpg", new File(rutaSalida));
                    }
                }
                
                // Cerrar el flujo de entrada del archivo Excel
            }

        } catch (IOException e) {
        }

        System.out.println("Proceso completado.");
    }
}

// C:\\Users\\SyD Colombia SA\\Desktop\\Libro2.xlsx




// Vladimir Estrada.
