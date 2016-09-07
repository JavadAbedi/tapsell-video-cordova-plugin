Tapsell Video ad Cordova plugin
====================
Tapsell Video ad plugin for Cordova and Phonegap.<br/>


- project home: [https://github.com/miladesign/tapsell-video-cordova-plugin](https://github.com/miladesign/tapsell-video-cordova-plugin)<br/>
- Tapsell Video android SDK<br/>
- Cordova version >3.0<br/>

### 1.install tapsell cordova plugin

download the plugin ,then install with local location

    cordova plugin add c:\tapsell_video_cordova_plugin 

### 2.init tapsell cordova plugin
init plugin after deviceready event <br />

    tapsell.setUp('Tapsell Key');
    
### 3.show video ad
after init ,show video ad and get results.

```
    this.video_info = {};
	var self = this;
	function ShowVideo(minAward,videoType) {
		tapsell.showVideo(minAward,videoType,function (result){
		self.video_info = { award: result["award"] };
		if (result["Complete"]==true)
		alert(GetVideoAward());
		if (result["Complete"]==false)
			alert('onNotComplete');
		if (result["Vavailable"]==false)
			alert('VonNotAvailable');
		});
       }
		
	function GetVideoAward() {
		return this.video_info.award;
	}
```

### 4.check cta
you can check video availablity before showing video ad.

```
	function CheckCTA(minAward,videoType) {
		tapsell.checkCTA(minAward,videoType,function (result) {
			if (result["available"]==true)
				alert('onAvailable');
			if (result["available"]==false)
				alert('onNotAvailable');
		});
	}
```

### Examples
<a href="https://github.com/miladesign/tapsell-video-cordova-plugin/blob/master/example/index.html">Click to see!</a><br>

### Useful links
Cordova Plugins<br>
http://miladesign.ir/cordova

## Credite and donate #
Created by: Milad Mohammadi<br>
Email: Rezagah.Milad@Gmail.com<br>
Web: http://miladesign.ir<br>
Telegram ID: @MilaDesign<br>
donate: http://miladesign.ir/donate