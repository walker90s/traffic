function currentTime() {
	var week = [ '日', '一', '二', '三', '四', '五', '六' ];
	var d = new Date(), str = '';
	str += d.getFullYear() + '年';
	str += d.getMonth() + 1 + '月';
	str += d.getDate() + '日 ';
	str += "星期" + week[d.getDay()]; //获取当前星期X(0-6,0代表星期天)
	/* str += d.getHours()+'时';
	 str  += d.getMinutes()+'分';
	 str+= d.getSeconds()+'秒';*/
	return str;
}