package com.repairs.message.resp;

import com.repairs.message.po.Image;



public class ImageMessage extends BaseMessage{
	private Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}
