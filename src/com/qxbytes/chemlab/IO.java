package com.qxbytes.chemlab;

import java.util.List;
/**
 * 
 * @author QxBytes
 *
 */
public interface IO {
	void Export(List<Element> e);
	List<Element> Import();
}
