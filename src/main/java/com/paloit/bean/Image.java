package com.paloit.bean;



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
