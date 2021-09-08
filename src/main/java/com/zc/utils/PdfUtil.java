package com.zc.utils;

import com.google.common.collect.Lists;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PdfUtil {
    public static final int DEFAULT_DPI = 150;
    /**
     * pdf转图片
     * 多页PDF会每页转换为一张图片，下面会有多页组合成一页的方法
     *
     * @param pdfFile pdf文件路径
     * @param outPath 图片输出路径
     */
    public static void pdf2multiImage(String pdfFile, String outPath) {
        try (PDDocument pdf = PDDocument.load(new FileInputStream(pdfFile))) {
            int actSize = pdf.getNumberOfPages();
            List<BufferedImage> picList = Lists.newArrayList();
            for (int i = 0; i < actSize; i++) {
                BufferedImage image = new PDFRenderer(pdf).renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
                picList.add(image);
            }
            // 组合图片
            ImageUtil.yPic(picList, outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
