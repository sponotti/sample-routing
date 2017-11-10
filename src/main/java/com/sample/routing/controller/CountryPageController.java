package com.sample.routing.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.routing.bean.Country;
import com.sample.routing.bean.Currency;

@Controller
@RequestMapping("/countries-page")
public class CountryPageController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getMovie() {

		List<Country> list = getList();
		ModelAndView model = new ModelAndView("list");
		model.addObject("lists", list);
		return model;

	}
	
	private List<Country> getList()  {

		List<Country> list = new ArrayList<Country>();

		
		RestTemplate restTemplate = new RestTemplate();
		String urlGETList = "https://restcountries.eu/rest/v2/all";
		ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(urlGETList, Object[].class);
		Object[] objects = responseEntity.getBody();
		MediaType contentType = responseEntity.getHeaders().getContentType();
		HttpStatus statusCode = responseEntity.getStatusCode();
		if(statusCode.equals(HttpStatus.OK)){
			if(objects != null && objects.length > 0){
				Country item;
				Currency c;
				LinkedHashMap<String, Object> map = null;
				List currList;
				LinkedHashMap<String, Object> mapCurr = null;
				List<Currency> l ;
				for(int i = 0; i < objects.length; i++){
					ObjectMapper mapper = new ObjectMapper();
					
					//JSON from String to Object
						if(objects[i] instanceof LinkedHashMap){
							map = (LinkedHashMap<String, Object>)objects[i];
							l = new ArrayList<Currency>();
							item = new Country(map.get("name").toString());
							currList = (ArrayList)map.get("currencies");
							for(int k=0;k<currList.size();k++){
								mapCurr = (LinkedHashMap<String, Object>)currList.get(k);
								c = new Currency();
								if(mapCurr.get("code") != null){
									c.setCode(mapCurr.get("code").toString());
								}
								if(mapCurr.get("name") != null){
									c.setName(mapCurr.get("name").toString());
								}
								if(mapCurr.get("symbol") != null){
									c.setSymbol(mapCurr.get("symbol").toString());
								}
								l.add(c);
							}
							
							item.setCurrencies(l);
							list.add(item);
						}
					
					
				}
			}
		}
		

		return list;

	}
	
}