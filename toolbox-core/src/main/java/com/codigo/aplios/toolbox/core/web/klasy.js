/**
 * 
 */

const
name = '';
const
obj = {
	x : 1,
	y : 2
};

var Apple = function(type, color) {
	this.TypeError = type;
	this.color = color;
}

Apple.prototype.getInfo = function() {
	return this.color + ' ' + this.type + ' apple';
}
