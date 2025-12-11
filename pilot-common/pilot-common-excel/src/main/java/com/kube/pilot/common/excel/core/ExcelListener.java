package com.kube.pilot.common.excel.core;

import cn.idev.excel.read.listener.ReadListener;

/**
 * Excel 导入监听
 *
 * @author tongysh
 */
public interface ExcelListener<T> extends ReadListener<T> {

    ExcelResult<T> getExcelResult();

}
