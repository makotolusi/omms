var defaultView = {
	render: function(target, container, frozen){
		var state = $.data(target, 'datagrid');
		var opts = state.options;
		var rows = state.data.rows;
		var fields = $(target).datagrid('getColumnFields', frozen);
		
		if (frozen){
			if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))){
				return;
			}
		}
		
		var table = ['<table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0"><tbody>'];
		for(var i=0; i<rows.length; i++) {
			// get the class and style attributes for this row
			var cls = (i % 2 && opts.striped) ? 'class="datagrid-row datagrid-row-alt"' : 'class="datagrid-row"';
			var styleValue = opts.rowStyler ? opts.rowStyler.call(target, i, rows[i]) : '';
			var style = styleValue ? 'style="' + styleValue + '"' : '';
			var rowId = state.rowIdPrefix + '-' + (frozen?1:2) + '-' + i;
			table.push('<tr id="' + rowId + '" datagrid-row-index="' + i + '" ' + cls + ' ' + style + '>');
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push('</tr>');
		}
		table.push('</tbody></table>');
		
		$(container).html(table.join(''));
	},
	
	renderFooter: function(target, container, frozen){
		var opts = $.data(target, 'datagrid').options;
		var rows = $.data(target, 'datagrid').footer || [];
		var fields = $(target).datagrid('getColumnFields', frozen);
		var table = ['<table class="datagrid-ftable" cellspacing="0" cellpadding="0" border="0"><tbody>'];
		
		for(var i=0; i<rows.length; i++){
			table.push('<tr class="datagrid-row" datagrid-row-index="' + i + '">');
			table.push(this.renderRow.call(this, target, fields, frozen, i, rows[i]));
			table.push('</tr>');
		}
		
		table.push('</tbody></table>');
		$(container).html(table.join(''));
	},
	
	renderRow: function(target, fields, frozen, rowIndex, rowData){
		var opts = $.data(target, 'datagrid').options;
		
		var cc = [];
		if (frozen && opts.rownumbers){
			var rownumber = rowIndex + 1;
			if (opts.pagination){
				rownumber += (opts.pageNumber-1)*opts.pageSize;
			}
			cc.push('<td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">'+rownumber+'</div></td>');
		}
		for(var i=0; i<fields.length; i++){
			var field = fields[i];
			var col = $(target).datagrid('getColumnOption', field);
			if (col){
				// get the cell style attribute
				var styleValue = col.styler ? (col.styler(rowData[field], rowData, rowIndex)||'') : '';
				var style = col.hidden ? 'style="display:none;' + styleValue + '"' : (styleValue ? 'style="' + styleValue + '"' : '');
				
				cc.push('<td field="' + field + '" ' + style + '>');
				
				if (col.checkbox){
					var style = '';
				} else {
					var style = '';
//						var style = 'width:' + (col.boxWidth) + 'px;';
					style += 'text-align:' + (col.align || 'left') + ';';
					if (!opts.nowrap){
						style += 'white-space:normal;height:auto;';
					} else if (opts.autoRowHeight){
						style += 'height:auto;';
					}
				}
				
				cc.push('<div style="' + style + '" ');
				if (col.checkbox){
					cc.push('class="datagrid-cell-check ');
				} else {
					cc.push('class="datagrid-cell ' + col.cellClass);
				}
				cc.push('">');
				
				if (col.checkbox){
					cc.push('<input type="checkbox" name="' + field + '" value="' + (rowData[field]!=undefined ? rowData[field] : '') + '"/>');
				} else if (col.formatter){
					cc.push(col.formatter(rowData[field], rowData, rowIndex));
				} else {
					cc.push(rowData[field]);
				}
				
				cc.push('</div>');
				cc.push('</td>');
			}
		}
		return cc.join('');
	},
	
	refreshRow: function(target, rowIndex){
		var row = {};
		var fields = $(target).datagrid('getColumnFields',true).concat($(target).datagrid('getColumnFields',false));
		for(var i=0; i<fields.length; i++){
			row[fields[i]] = undefined;
		}
		var rows = $(target).datagrid('getRows');
		$.extend(row, rows[rowIndex]);
		this.updateRow.call(this, target, rowIndex, row);
	},
	
	updateRow: function(target, rowIndex, row){
		var opts = $.data(target, 'datagrid').options;
		var rows = $(target).datagrid('getRows');
		
		var tr = opts.finder.getTr(target, rowIndex);
		for(var field in row){
			rows[rowIndex][field] = row[field];
			var td = tr.children('td[field="' + field + '"]');
			var cell = td.find('div.datagrid-cell');
			var col = $(target).datagrid('getColumnOption', field);
			if (col){
				var styleValue = col.styler ? col.styler(rows[rowIndex][field], rows[rowIndex], rowIndex) : '';
				td.attr('style', styleValue || '');
				if (col.hidden){
					td.hide();
				}
				
				if (col.formatter){
					cell.html(col.formatter(rows[rowIndex][field], rows[rowIndex], rowIndex));
				} else {
					cell.html(rows[rowIndex][field]);
				}
			}
		}
		var styleValue = opts.rowStyler ? opts.rowStyler.call(target, rowIndex, rows[rowIndex]) : '';
		tr.attr('style', styleValue || '');
		$(target).datagrid('fixRowHeight', rowIndex);
	},
	
	insertRow: function(target, index, row){
		var opts = $.data(target, 'datagrid').options;
		var dc = $.data(target, 'datagrid').dc;
		var data = $.data(target, 'datagrid').data;
		
		if (index == undefined || index == null) index = data.rows.length;
		if (index > data.rows.length) index = data.rows.length;
		
		for(var i=data.rows.length-1; i>=index; i--){
			opts.finder.getTr(target, i, 'body', 2).attr('datagrid-row-index', i+1);
			var tr = opts.finder.getTr(target, i, 'body', 1).attr('datagrid-row-index', i+1);
			if (opts.rownumbers){
				tr.find('div.datagrid-cell-rownumber').html(i+2);
			}
		}
		
		var fields1 = $(target).datagrid('getColumnFields', true);
		var fields2 = $(target).datagrid('getColumnFields', false);
		var tr1 = '<tr class="datagrid-row" datagrid-row-index="' + index + '">' + this.renderRow.call(this, target, fields1, true, index, row) + '</tr>';
		var tr2 = '<tr class="datagrid-row" datagrid-row-index="' + index + '">' + this.renderRow.call(this, target, fields2, false, index, row) + '</tr>';
		if (index >= data.rows.length){	// append new row
			if (data.rows.length){	// not empty
				opts.finder.getTr(target, '', 'last', 1).after(tr1);
				opts.finder.getTr(target, '', 'last', 2).after(tr2);
			} else {
				dc.body1.html('<table cellspacing="0" cellpadding="0" border="0"><tbody>' + tr1 + '</tbody></table>');
				dc.body2.html('<table cellspacing="0" cellpadding="0" border="0"><tbody>' + tr2 + '</tbody></table>');
			}
		} else {	// insert new row
			opts.finder.getTr(target, index+1, 'body', 1).before(tr1);
			opts.finder.getTr(target, index+1, 'body', 2).before(tr2);
		}
		
		data.total += 1;
		data.rows.splice(index, 0, row);
		
		this.refreshRow.call(this, target, index);
	},
	
	deleteRow: function(target, index){
		var opts = $.data(target, 'datagrid').options;
		var data = $.data(target, 'datagrid').data;
		
		opts.finder.getTr(target, index).remove();
		for(var i=index+1; i<data.rows.length; i++){
			opts.finder.getTr(target, i, 'body', 2).attr('datagrid-row-index', i-1);
			var tr1 = opts.finder.getTr(target, i, 'body', 1).attr('datagrid-row-index', i-1);
			if (opts.rownumbers){
				tr1.find('div.datagrid-cell-rownumber').html(i);
			}
		}
		
		data.total -= 1;
		data.rows.splice(index,1);
	},
	
	onBeforeRender: function(target, rows){},
	onAfterRender: function(target){
		var opts = $.data(target, 'datagrid').options;
		if (opts.showFooter){
			var footer = $(target).datagrid('getPanel').find('div.datagrid-footer');
			footer.find('div.datagrid-cell-rownumber,div.datagrid-cell-check').css('visibility', 'hidden');
		}
	}
};
