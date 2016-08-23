module.exports = {
	setUp: function(Key) {
		cordova.exec(function (result) {console.log("Tapsell Video Initialize Success (By: MilaDesign.ir)");}, null, "TapsellVideo", "setUp",[ Key ]); 
    },
	showVideo: function(minAward, videoType, successCallback) {
		cordova.exec(successCallback, null, "TapsellVideo", "showVideo", [ minAward, videoType ]);		
	},
	checkCTA: function(minAward, videoType, successCallback) {
		cordova.exec(successCallback, null, "TapsellVideo", "checkCTA", [ minAward, videoType ]);		
	}
};