var easyuiModule = angular.module('common.ui.easyui', []);

easyuiModule.directive('aePanel', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).panel({});
		}
	};
});

easyuiModule.directive('aeLayout', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).layout({});
		}
	};
});

easyuiModule.directive('aeTabs', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).tabs({});
		}
	};
});

easyuiModule.directive('aeWindow', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).window({});
		}
	};
});

easyuiModule.directive('aeDialog', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).dialog({});
		}
	};
});

easyuiModule.directive('aeDatagrid', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			queryDateParams : '=',
			onSelectFunction : '=',
			onUnselectFunction : '='
		},
		link : function(scope, elm, attr) {
			if (scope.queryDateParams) {
				$(elm).datagrid({
					queryParams : scope.queryDateParams,
					onSelect : function(index, row) {
						scope.$apply(function() {
							if (scope.onSelectFunction != null) {
								scope.onSelectFunction(index, row);
							}
						});
					},
					onUnselect : function(index, row) {
						scope.$apply(function() {
							if (scope.onSelectFunction != null) {
								scope.onSelectFunction(index, row);
							}
						});
					}
				});
			} else {
				$(elm).datagrid({
					onSelect : function(index, row) {
						scope.$apply(function() {
							if (scope.onSelectFunction != null) {
								scope.onSelectFunction(index, row);
							}
						});
					},
					onUnselect : function(index, row) {
						scope.$apply(function() {
							if (scope.onSelectFunction != null) {
								scope.onSelectFunction(index, row);
							}
						});
					}
				});
			}
		}
	};
});

easyuiModule.directive('aeTree', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			onSelectFunction : '='
		},
		link : function(scope, elm, attr) {
			$(elm).tree({
				onSelect : function(node) {
					scope.$apply(function() {
						if (scope.onSelectFunction != null) {
							scope.onSelectFunction(node);
						}
					});
				}
			});
		}
	};
});

easyuiModule.directive('aeLinkbutton', function(permissionService) {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			onClickFunction : '=',
			permissions : '@'
		},
		link : function(scope, elm, attr) {
			$(elm).css("width","10%");
			if (scope.permissions) {
				var permissionArray = scope.permissions.split(",");
				var isCheck = false;
				for (var i = 0; i < permissionArray.length; i++) {
					isCheck = permissionService.check(permissionArray[i]);
					if (isCheck) {
						break;
					}
				}
				if (isCheck) {
					$(elm).linkbutton({
						onClick : function() {
							if (scope.onClickFunction != null) {
								scope.onClickFunction();
							}
						}
					});
				} else {
					elm.remove();
					elm = null;
				}
			} else {
				$(elm).linkbutton({
					onClick : function() {
						if (scope.onClickFunction != null) {
							scope.onClickFunction();
						}
					}
				});
			}
		}
	};
});

easyuiModule.directive('aeTextbox', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			textboxValue : '=',
			onClickButtonFunction : '='
		},
		link : function(scope, elm, attr) {
			$(elm).textbox({
				value : scope.textboxValue,
				onChange : function(newValue, oldValue) {
					scope.$apply(function() {
						scope.textboxValue = $.trim(newValue);
						$(elm).textbox("setValue",$.trim(newValue));
					});
				},
				onClickButton : function() {
					if (scope.onClickButtonFunction != null) {
						scope.onClickButtonFunction();
					}
				}
			});
		}
	};
});

easyuiModule.directive('aeNumberbox', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			numberboxValue : '=',
			onClickButtonFunction : '='
		},
		link : function(scope, elm, attr) {
			$(elm).numberbox({
				value : scope.numberboxValue,
				onChange : function(newValue, oldValue) {
					scope.$apply(function() {
						scope.numberboxValue = $.trim(newValue);
						$(elm).numberbox("setValue",$.trim(newValue));
					});
				},
				onClickButton : function() {
					if (scope.onClickButtonFunction != null) {
						scope.onClickButtonFunction();
					}
				}
			});
		}
	};
});

easyuiModule.directive('aeDatebox', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			dateboxValue : '=',
			dateboxButtons : '=',
			validatorFunction : '=',
			dateboxType:"@",
		},
		link : function(scope, elm, attr) {
			if (scope.dateboxButtons !== null) {
				$(elm).datebox({
					onSelect : function(date) {
						scope.dateboxValue = date;
					},
					buttons : scope.dateboxButtons
				});
			} else {
				$(elm).datebox({
					onSelect : function(date) {
						scope.dateboxValue = date;
					}
				});
			}
			
			/*$(elm).datebox('calendar').calendar({
				validator : function(date) {
					var value = true;
					if (scope.validatorFunction !== null) {
						value = scope.validatorFunction(date);
					}
					return value;
				}
			});*/
			$(elm).datebox('setValue', scope.dateboxValue);
		}
	};
});

easyuiModule.directive('aeCombobox', function() {
	return {
		restrict : 'EA',
		replace : true,
		scope : {
			comboboxValue : '='
		},
		link : function(scope, elm, attr) {
			$(elm).combobox({
				onSelect : function(record) {
					scope.$apply(function() {
						scope.comboboxValue = record.id;
					});
					scope.$apply(function() {
						scope.comboboxValue = record.id;
					});
				}
			});
			$(elm).combobox('setValue', scope.comboboxValue);
		}
	};
});

easyuiModule.directive('aeFilebox', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			$(elm).filebox({});
		}
	};
});

easyuiModule.directive('aeLabel', function() {
	return {
		restrict : 'EA',
		replace : true,
		link : function(scope, elm, attr) {
			var style = "display: inline-block;";
			if(attr.width) {
				style += "width: "+attr.width+";";
			}
			$(elm).html("<label style='"+style+"'>"+attr.name+":</label>");
		}
	};
});

$.extend($.fn.validatebox.defaults.rules, {
	CHS : {
		validator : function(value, param) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '请输入汉字'
	},
	ZIP : {
		validator : function(value, param) {
			return /^[1-9]\d{5}$/.test(value);
		},
		message : '邮政编码不存在'
	},
	mobile : {
		validator : function(value, param) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(13|15|18)\d{9}$/.test(value);
		},
		message : '手机号码不正确'
	},
	phoneOrMobile : {
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value)
					|| /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '请填入手机或电话号码,如13688888888或020-8888888'
	},
	idcard : {
		validator : function(value, param) {
			return idCard(value);
		},
		message : '请输入正确的身份证号码'
	}
});

var idCard = function(value) {
	if (value.length == 18 && 18 != value.length)
		return false;
	var number = value.toLowerCase();
	var d, sum = 0, v = '10x98765432', w = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
	var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
	if (re == null || a.indexOf(re[1]) < 0)
		return false;
	if (re[2].length == 9) {
		number = number.substr(0, 6) + '19' + number.substr(6);
		d = [ '19' + re[4], re[5], re[6] ].join('-');
	} else
		d = [ re[9], re[10], re[11] ].join('-');
	if (!isDateTime.call(d, 'yyyy-MM-dd'))
		return false;
	for (var i = 0; i < 17; i++)
		sum += number.charAt(i) * w[i];
	return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}

var isDateTime = function(format, reObj) {
	format = format || 'yyyy-MM-dd';
	var input = this, o = {}, d = new Date();
	var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input
			.split(/\d+/g);
	var len = f1.length, len1 = f3.length;
	if (len != f2.length || len1 != f4.length)
		return false;
	for (var i = 0; i < len1; i++)
		if (f3[i] != f4[i])
			return false;
	for (var i = 0; i < len; i++)
		o[f1[i]] = f2[i];
	o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
	o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
	o.dd = s(o.dd, o.d, d.getDate(), 31);
	o.hh = s(o.hh, o.h, d.getHours(), 24);
	o.mm = s(o.mm, o.m, d.getMinutes());
	o.ss = s(o.ss, o.s, d.getSeconds());
	o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
	if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0)
		return false;
	if (o.yyyy < 100)
		o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
	d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
	var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh
			&& d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
	return reVal && reObj ? d : reVal;

	function s(s1, s2, s3, s4, s5) {
		s4 = s4 || 60, s5 = s5 || 2;
		var reVal = s3;
		if (s1 != undefined && s1 != '' || !isNaN(s1))
			reVal = s1 * 1;
		if (s2 != undefined && s2 != '' && !isNaN(s2))
			reVal = s2 * 1;
		return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
	}
}
