package com.paloit.bean;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.paloit.manager.NewsManager;


public class Image {
	
	// =========================================================================
	// ATTRIBUTS
	// =========================================================================
	// Init ---------------------------------------------------------------------------------------

    private String id;
    private String name;
    private String contentType;
    private byte[] content;


		// =========================================================================
		// CONSTRUCTEURS
		// =========================================================================

		
		public Image() {	
			
		}



		// =========================================================================
		// METHODS
		// =========================================================================
	
		
		// =========================================================================
		// @Autowired
		// =========================================================================
	

		

		// =========================================================================
		// GETTERS & SETTERS
		// =========================================================================

		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getContentType() {
			return contentType;
		}


		public void setContentType(String contentType) {
			this.contentType = contentType;
		}


		public byte[] getContent() {
			return content;
		}


		public void setContent(byte[] content) {
			this.content = content;
		}
		

}
