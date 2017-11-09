//https://jsfiddle.net/livibetter/HV9HM/ stick

function sticky_relocate() {
	var window_top = $(window).scrollTop();
	var div_top = $('#sticky-anchor').offset().top;
	if (window_top > div_top) {
		$('#sticky').addClass('stick');
		$('#sticky-anchor').height($('#sticky').outerHeight());
	} else {
		$('#sticky').removeClass('stick');
		$('#sticky-anchor').height(0);
	}
}

$(function() {
	$(window).scroll(sticky_relocate);
	sticky_relocate();
});

var dir = 1;
var MIN_TOP = 200;
var MAX_TOP = 350;

/*
 * 
 */
function autoscroll() {
	var person = {name:'Andrzej', surname: 'Radziszewski', function: { return null; }};
	
	var window_top = $(window).scrollTop() + dir;
	if (window_top >= MAX_TOP) {
		window_top = MAX_TOP;
		dir = -1;
	} else if (window_top <= MIN_TOP) {
		window_top = MIN_TOP;
		dir = 1;
	}
	$(window).scrollTop(window_top);
	window.setTimeout(autoscroll, 100);
}

#sticky {
    padding: 0.5ex;
    width: 600px;
    background-color: #333;
    color: #fff;
    font-size: 2em;
    border-radius: 0.5ex;
}

#sticky.stick {
    margin-top: 0 !important;
    position: fixed;
    top: 0;
    z-index: 10000;
    border-radius: 0 0 0.5em 0.5em;
}

<div id="sticky-anchor"></div>
<div id="sticky">This will stay at top of page</div>