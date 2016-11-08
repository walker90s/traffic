package com.tt.traffic.shptools;



import java.io.FileReader;

import org.apache.commons.lang.StringUtils;

import au.com.bytecode.opencsv.CSVReader;
import jxl.Sheet;
import jxl.Workbook;

public class CsvInput {
//	public void importCsvFile() {  
//	      
//	    CSVReader csvReader = null;  
//	      
//	    try {  
//	        csvReader = new CSVReader(new FileReader(importFile),',');//importFile为要导入的文本格式逗号分隔的csv文件，提供getXX/setXX方法  
//	          
//	        if(csvReader != null){  
//	              
//	            //first row is title, so past  
//	            csvReader.readNext();  
//	            String[] csvRow = null;//row  
//	              
//	            while ((csvRow = csvReader.readNext()) != null){  
//	                  
//	                for (int i =0; i<csvRow.length; i++){  
//	                      
//	                    String temp = csvRow[i];  
//	                    switch (i) {  
//	                        case 0:  
//	                            if(StringUtils.isNotEmpty(temp)){  
//	                                linkman.setLinkmanName(temp);  
//	                            }  
//	                            break;  
//	                        case 1:  
//	                            if(StringUtils.isNotEmpty(temp)){  
//	                                linkman.setLinkmanEmail(temp);  
//	                            }  
//	                            break;  
//	                        default:  
//	                            break;  
//	                    }  
//	                }  
//	                  
//	                //保存linkman到数据库  
//	                if(linkman.getLinkmanName() != null && linkman.getLinkmanEmail() != null){  
//	                    EmailLinkmanAPI.insertLinkman(linkman);  
//	                }  
//	            }  
//	        }  
//	    } catch (Exception e) {  
//	        e.printStackTrace();  
//	    }   
//	      
//	}  
//	
//	
//	public void importXlsFile() {  
//	      
//	    Workbook book = null;  
//	    try {  
//	        book = Workbook.getWorkbook(importFile);//importFile为要导入的xls文件，或二进制的csv文件，提供getXX/setXX方法  
//	    } catch (Exception e) {  
//	        e.printStackTrace();  
//	    }   
//	      
//	    if(book != null){  
//	          
//	        int sheetNo = book.getNumberOfSheets();  
//	          
//	        for(int i = 0; i < sheetNo; i++){  
//	              
//	            Sheet sheet=book.getSheet(i);  
//	            int rowNum = sheet.getRows();  
//	            int colNum = sheet.getColumns();  
//	              
//	            //first row is title, so past  
//	            for(int r = 1; r < rowNum; r++){//行  
//	                  
//	                for(int c = 0; c < colNum;c++){//列  
//	                      
//	                    String temp = sheet.getCell(c,r).getContents();  
//	                    switch (c) {  
//	                        case 0:  
//	                            if(StringUtils.isNotEmpty(temp)){  
//	                                linkman.setLinkmanName(temp);  
//	                            }  
//	                            break;  
//	                        case 1:  
//	                            if(StringUtils.isNotEmpty(temp)){  
//	                                linkman.setLinkmanEmail(temp);  
//	                            }  
//	                            break;  
//	                        default:  
//	                            break;  
//	                    }  
//	                }  
//	                  
//	                //保存linkman到数据库  
//	                if(linkman.getLinkmanName() != null && linkman.getLinkmanEmail() != null){  
//	                    EmailLinkmanAPI.insertLinkman(linkman);  
//	                }  
//	            }  
//	        }  
//	    }  
//	}  
}
