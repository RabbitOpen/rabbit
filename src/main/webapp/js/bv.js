(function ($) {
	if(/msie/.test(navigator.userAgent.toLowerCase())){
		if(-1 != navigator.userAgent.toLowerCase().indexOf("msie 9.0")){
			window.open("./support.html","_self");
		}
		if (!$.support.leadingWhitespace) {
			window.open("./support.html","_self");
		}
	}
 })(jQuery);