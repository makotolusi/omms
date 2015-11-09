var WxFullCalendarDefaults={
	// time formats
	titleFormat: {
		month: 'yyyy年 MM月',
		week: "yyyy年 MM月 dd日{ '&#8212;' yyyy年 MM月 dd日}",
		day: 'yyyy年 MM月 dd日, dddd'
	},
	columnFormat: {
		month: 'dddd',
		week: 'MM月dd日 ddd',
		day: 'yyyy年MM月dd日 dddd'
	},
	timeFormat: { // for event elements
		'': 'h(:mm)t' // default
	},
	monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	monthNamesShort: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
	dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
	buttonText: {
		prev: '&nbsp;&#9668;&nbsp;',
		next: '&nbsp;&#9658;&nbsp;',
		prevYear: '&nbsp;&lt;&lt;&nbsp;',
		nextYear: '&nbsp;&gt;&gt;&nbsp;',
		today: '今天',
		month: '月',
		week: '周',
		day: '日'
	},
	weekMode: 'fixed',
	allDaySlot: true,
	allDayText: '全天',
	firstHour: 6,
	slotMinutes: 30,
	defaultEventMinutes: 120,
	axisFormat: 'hh:mm',
	timeFormat: {
		agenda: 'hh:mm{ - hh:mm}'
	},
	dragOpacity: {
		agenda: .5
	},
	minTime: 0,
	maxTime: 24
};
