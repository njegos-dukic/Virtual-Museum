package org.unibl.etf.virtualmuseum.entities;

public class ArtifactEntity {
	
	private Integer artifactId;
	private String uri;
	private String type;
	private Integer tourId;
	
	public ArtifactEntity(Integer artifactId, String uri, String type, Integer tourId) {
		super();
		this.artifactId = artifactId;
		this.uri = uri;
		this.type = type;
		this.tourId = tourId;
	}

	public Integer getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(Integer artifactId) {
		this.artifactId = artifactId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTourId() {
		return tourId;
	}

	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}
}
