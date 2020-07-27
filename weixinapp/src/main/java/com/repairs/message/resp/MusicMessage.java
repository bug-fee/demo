package com.repairs.message.resp;

import com.repairs.message.po.Music;

public class MusicMessage extends BaseMessage{
	private Music music;

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}
	
}
