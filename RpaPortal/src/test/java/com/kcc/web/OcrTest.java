package com.kcc.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.gax.paging.Page;
import com.google.api.services.storage.model.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

public class OcrTest {
	private static final Logger logger = LoggerFactory.getLogger(OcrTest.class);
	
	public static void main(String[] args) {
		// 스토리지 버킷 목록 조회
		/*
		Storage storage = StorageOptions.getDefaultInstance().getService();
		System.out.println("Buckets:");
		Page<com.google.cloud.storage.Bucket> buckets = storage.list();
		for (com.google.cloud.storage.Bucket bucket : buckets.iterateAll()) {
			System.out.println(bucket.toString());
		}
		*/
		
		try {
			String imageFilePath = "D:\\Images\\1.jpg";
			
			List<AnnotateImageRequest> requests = new ArrayList<>();
			ByteString imgBytes = ByteString.readFrom(new FileInputStream(imageFilePath));

			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			AnnotateImageRequest request =
				AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			requests.add(request);
			
			// Initialize client that will be used to send requests. This client only needs to be created
			// once, and can be reused for multiple requests. After completing all of your requests, call
			// the "close" method on the client to safely clean up any remaining background resources.
			try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
				BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			    List<AnnotateImageResponse> responses = response.getResponsesList();
			    
			    for (AnnotateImageResponse res : responses) {
			    	
			    	// For full list of available annotations, see http://g.co/cloud/vision/docs
			    	/*
					TextAnnotation annotation = res.getFullTextAnnotation();
					for (com.google.cloud.vision.v1.Page page: annotation.getPagesList()) {
						String pageText = "";
						for (Block block : page.getBlocksList()) {
							String blockText = "";
							for (Paragraph para : block.getParagraphsList()) {
								String paraText = "";
								for (Word word: para.getWordsList()) {
									String wordText = "";
									for (Symbol symbol: word.getSymbolsList()) {
										wordText = wordText + symbol.getText();
										System.out.format("Symbol text: %s (confidence: %f)\n", symbol.getText(),
												symbol.getConfidence());
									}
									System.out.format("Word text: %s (confidence: %f)\n\n", wordText, word.getConfidence());
									paraText = String.format("%s %s", paraText, wordText);
								}
								// Output Example using Paragraph:
								System.out.println("\nParagraph: \n" + paraText);
								System.out.format("Paragraph Confidence: %f\n", para.getConfidence());
								blockText = blockText + paraText;
							}
							pageText = pageText + blockText;
						}
					}
					System.out.println("\nComplete annotation:");
					System.out.println(annotation.getText());
					*/
			    	
			    	if (res.hasError()) {
			    		System.out.format("Error: %s%n", res.getError().getMessage());
			    		return;
			    	}
			    	
			    	System.out.println(res.getTextAnnotationsList().get(0).getDescription());
		    		
			    	/*
			    	// For full list of available annotations, see http://g.co/cloud/vision/docs
			    	for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
			    		System.out.format("Text: %s%n", annotation.getDescription());
			    		//System.out.format("Position : %s%n", annotation.getBoundingPoly());
			    	}
					*/
			    	
		    	}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
