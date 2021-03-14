//package com.example.eshopping.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.FileOutputStream;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.example.eshopping.entity.Order;
//import com.example.eshopping.entity.OrderMaster;
//import com.example.eshopping.entity.UserDetails;
//import com.example.eshopping.service.UserService;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.io.image.ImageData;
//import com.itextpdf.io.image.ImageDataFactory;
////import com.itextpdf.layout.element.Image;
//
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//@Component
//public class PdfGenerator {
//
//	@Autowired
//	UserService userService;
//
//	private static String FILE = "F://Mukesh/test.pdf";
//    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
//            Font.BOLD);
//    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//            Font.NORMAL, BaseColor.RED);
//    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
//            Font.BOLD);
//    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
//            Font.BOLD);
//
//    public ByteArrayOutputStream citiesReport(Order order, OrderMaster orderList) {
//
//        Document document = new Document();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        UserDetails userDetails = userService.findUserDetailsById(orderList.getSellerId());
//
//        try {
//        	 PdfWriter.getInstance(document, out);
//        	String imageFile ="C://Users/Padhu/Downloads/logoNew.png";
//        	ImageData data = ImageDataFactory.create(imageFile);
//        	data.setWidth(300);
////        	 Image image = new Image(data);
////        	document.add(data);
//
//        	Paragraph para = new Paragraph();
//        	para.add(new Chunk("\n Invoice:"+order.getId()));
//        	para.add(new Chunk("\n GST: "+userDetails.getGstin()));
//        	para.setAlignment(Element.ALIGN_LEFT);
////        	document.add(para);
//        	Paragraph addressPara = new Paragraph();
//        	addressPara.add(userDetails.getStoreName());
//        	addressPara.add(new Chunk("\n "+userDetails.getAddress()));
//        	addressPara.add(new Chunk("\n "+userDetails.getCity()));
//        	addressPara.add(new Chunk("\n "+userDetails.getState()+"-"+userDetails.getZipCode()));
//            PdfPTable table = new PdfPTable(2);
//            table.setWidthPercentage(100);
//            table.setWidths(new int[]{ 3, 3});
//
//            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//
//            PdfPCell hcell;
//            hcell = new PdfPCell(new Phrase("Billing Address", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase("Shipping Address", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            table.addCell(hcell);
//
//
////            for (City city : cities) {
//
//                PdfPCell cell;
//
//                cell = new PdfPCell(new Phrase(order.getShippingAddress().getAddress()+","+order.getShippingAddress().getCity()+","+order.getShippingAddress().getState()+
//                		","+order.getShippingAddress().getPincode()));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
//                cell = new PdfPCell(new Phrase(order.getShippingAddress().getAddress()+","+order.getShippingAddress().getCity()+","+order.getShippingAddress().getState()+
//                		","+order.getShippingAddress().getPincode()));
//                cell.setPaddingLeft(5);
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell);
//
////            }
//
//            PdfPTable productTable = new PdfPTable(7);
//            productTable.setPaddingTop(1f);
//            productTable.setWidthPercentage(100);
////            table.setWidths(new int[] {1, 3, 3, 3, 1});
//            float[] columnWidths = new float[]{10f, 30f, 10f, 10f, 10f, 10f, 10f};
//            productTable.setWidths(columnWidths);
//
//            PdfPCell hcell1;
//            hcell1 = new PdfPCell(new Phrase("S.No", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("Product", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("Unit Price", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("Quantity", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("GST", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("GST Price", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("Handling Fee", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//
//            hcell1 = new PdfPCell(new Phrase("Net Amount", headFont));
//            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(hcell1);
//            int i = 1;
//            double totalPrice =0.00;
////            for(OrderMaster orderList : orderMaster) {
////            	System.out.println("order L:ost "+JSONUtil.toJson(orderList));
//            	 PdfPCell productCell;
//            	 productCell = new PdfPCell(new Phrase(String.valueOf(i), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//                 productCell = new PdfPCell(new Phrase(orderList.getProductName(), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//                 String gstPriceInString = null;
//                 if(orderList.getGst() == null) {
//                	 gstPriceInString = "1";
//                 }
//                 else {
//                	 gstPriceInString = orderList.getGst();
//                 }
//                 double gstPrice = (Double.parseDouble(orderList.getPrice()) * Double.parseDouble(gstPriceInString))/100;
//                 double originalPrice = Double.parseDouble(orderList.getPrice())- gstPrice ;
//                 BigDecimal originalBd = new BigDecimal(originalPrice).setScale(2, RoundingMode.HALF_UP);
//
//                 productCell = new PdfPCell(new Phrase(String.valueOf(originalBd.doubleValue()), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//                 productCell = new PdfPCell(new Phrase(orderList.getQuantity(), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//                 productCell = new PdfPCell(new Phrase(orderList.getGst()+"%", headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//                 productCell = new PdfPCell(new Phrase(String.valueOf(gstPrice), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//                 double price = Double.parseDouble(orderList.getQuantity()) * Double.parseDouble(orderList.getPrice());
//                 BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
//                 totalPrice+=bd.doubleValue();
//                 productCell = new PdfPCell(new Phrase(String.valueOf(bd.doubleValue()), headFont));
//            	 productCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//                 productTable.addCell(productCell);
//
//
//
//            PdfPCell shippingCell;
//            shippingCell = new PdfPCell(new Phrase("Shipping Charge", headFont));
//            shippingCell.setColspan(6);
//            shippingCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            productTable.addCell(shippingCell);
//
//            shippingCell = new PdfPCell(new Phrase(String.valueOf(order.getShippingCharge()), headFont));
//            shippingCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(shippingCell);
//
//            PdfPCell handlingFeeCell;
//            handlingFeeCell = new PdfPCell(new Phrase("Handling Fee", headFont));
//            handlingFeeCell.setColspan(6);
//            handlingFeeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            productTable.addCell(handlingFeeCell);
//
//            handlingFeeCell = new PdfPCell(new Phrase(String.valueOf(orderList.getHandlingFee()), headFont));
//            handlingFeeCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(handlingFeeCell);
//
//            PdfPCell totalPriceCell;
//            totalPriceCell = new PdfPCell(new Phrase("Total", headFont));
//            totalPriceCell.setColspan(6);
//            totalPriceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            productTable.addCell(totalPriceCell);
//
//            totalPrice+=order.getShippingCharge();
//            BigDecimal bd1 = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
//
//            totalPriceCell = new PdfPCell(new Phrase(String.valueOf(bd1.doubleValue()), headFont));
//            totalPriceCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//            productTable.addCell(totalPriceCell);
////            Image image = new Image(data);
//            document.open();
//
//            document.addTitle("Invoice");
//            document.add(Chunk.NEWLINE);
//            document.add(para);
//            document.add(Chunk.NEWLINE);
//            document.add(addressPara);
//            document.add(Chunk.NEWLINE);
//            document.add(table);
//            document.add(Chunk.NEWLINE);
//            document.add(productTable);
//            document.close();
//
//        } catch (Exception ex) {
//
//            System.out.println("Error occurred: {0}"+ex );
//        }
//
//        return out;
//    }
//}
