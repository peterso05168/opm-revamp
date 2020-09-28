package com.opm.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemParamWithImageList {
	private SystemParam systemParam;
	private List<Image> imageList;
}
