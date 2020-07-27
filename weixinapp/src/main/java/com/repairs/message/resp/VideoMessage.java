package com.repairs.message.resp;

import com.repairs.message.po.Video;

public class VideoMessage extends BaseMessage{
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
}
