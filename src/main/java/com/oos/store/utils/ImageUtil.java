/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oos.store.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Oluwaseun_Smart
 */
public class ImageUtil {

    public static byte[] imageToByteArray(BufferedImage bufferedImage) {
        byte[] imageInByte = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return imageInByte;
    }

    public static BufferedImage byteArrayToBufferedImage(byte[] imageInByte) {
        InputStream is = new ByteArrayInputStream(imageInByte);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(is);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return bufferedImage;
    }
}
