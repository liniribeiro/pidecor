package mock;

import io.spring.guides.gs_producing_web_service.AllItems;
import io.spring.guides.gs_producing_web_service.AllProviders;
import io.spring.guides.gs_producing_web_service.Item;
import io.spring.guides.gs_producing_web_service.Provider;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ProviderRepository {
	private final Map<String, Provider> providersByName = new HashMap<>();
	private final Map<String, Item> providersItemsById = new HashMap<>();

	@PostConstruct
	public void initData() {
		Provider provider1 = new Provider();
		provider1.setId("7885323535");
		provider1.setName("Provider 1 - Pillow's By LILI");
		provider1.setSocianName("Pillow's By LILI LTDA.");
		provider1.setCnpj("39.670.453/0001-60");
		provider1.setAdress("Rua 13 de xanana, 5668");
		provider1.setCity("Blumenau / SC");
		provider1.setPhone("+55 (58) 95533-4985");
		provider1.setHomepage("www.lili-pillow.com");
		provider1.setEmail("contact-lili@pillow.com");
		provider1.setCompanyType("MULTINACIONAL");
		provider1.setAnnualBilling("BETWEEN_1_10_MILLION");
		provider1.setFundationYear(2003);
		provider1.setClientNumber(70);
		provider1.setReputation("GOOD");
		provider1.setCategory("PILLOW");
		provider1.setItems(10);
		provider1.setPercent(5);
		provider1.setDeliveryDays(10);
		provider1.setRejectionRate(8.5);
		provider1.setQtdItemsSold(400);
		providersByName.put(provider1.getId(), provider1);

		Provider provider2 = new Provider();
		provider2.setId("4303614225");
		provider2.setName("Provider 2 - Xuxu's Forniture");
		provider2.setSocianName("Forniture's By XUXU LTDA.");
		provider2.setCnpj("48.628.120/0001-64");
		provider2.setAdress("Rua 88 de xanana, 56");
		provider2.setCity("São Paulo / SP");
		provider2.setPhone("+55 (41) 957833-4977");
		provider2.setHomepage("www.xuxu-forniture.com");
		provider2.setEmail("contact-lxuxu@forniture.com");
		provider2.setCompanyType("MICRO");
		provider2.setAnnualBilling("UNTILL_100_MIL");
		provider2.setFundationYear(2010);
		provider2.setClientNumber(20);
		provider2.setReputation("BAD");
		provider2.setCategory("FORNITURE");
		provider2.setItems(15);
		provider2.setPercent(10);
		provider2.setDeliveryDays(6);
		provider2.setRejectionRate(38.5);
		provider2.setQtdItemsSold(100);
		providersByName.put(provider2.getId(), provider2);

		Provider provider3 = new Provider();
		provider3.setId("5921262654");
		provider3.setName("Provider 3 - Lights Ilumination GO");
		provider3.setSocianName("Lights Ilumination GO LTDA.");
		provider3.setCnpj("77.656.275/0001-02");
		provider3.setAdress("Rua Tacaca xanana, 5668");
		provider3.setCity("Blumenau / SC");
		provider3.setPhone("+55 (47) 95533-7885");
		provider3.setHomepage("www.lili-pillow.com");
		provider3.setEmail("contact-lili@pillow.com");
		provider3.setCompanyType("MEDIUM");
		provider3.setAnnualBilling("OVER_100_MILLION");
		provider3.setFundationYear(2013);
		provider3.setClientNumber(52);
		provider3.setReputation("GOOD");
		provider3.setCategory("LIGHTS");
		provider3.setItems(1);
		provider3.setPercent(15);
		provider3.setDeliveryDays(20);
		provider3.setRejectionRate(2.3);
		provider3.setQtdItemsSold(250);

		providersByName.put(provider3.getId(), provider3);

		Provider provider4 = new Provider();
		provider4.setId("7872963360");
		provider4.setName("Provider 4 - Clouds Pi-llow");
		provider4.setSocianName("Clouds PI-llow LTDA.");
		provider4.setCnpj("85.727.696/0001-05");
		provider4.setAdress("Rua Tassdcaca xanana, 58");
		provider4.setCity("Rio de Janeiro / RJ");
		provider4.setPhone("+55 (88) 98877-7885");
		provider4.setHomepage("www.clouds-pi-llow.com");
		provider4.setEmail("contact-clouds@pi-llow.com");
		provider4.setCompanyType("SMALL");
		provider4.setAnnualBilling("BETWEEN_1_10_MILLION");
		provider4.setFundationYear(2016);
		provider4.setClientNumber(150);
		provider4.setReputation("VERRY_GOOD");
		provider4.setCategory("PILLOW");
		provider4.setItems(100);
		provider4.setPercent(15);
		provider4.setDeliveryDays(10);
		provider4.setRejectionRate(0.3);
		provider4.setQtdItemsSold(278);
		providersByName.put(provider4.getId(), provider4);

		Provider provider5 = new Provider();

		provider5.setId("7872963388760");
		provider5.setName("Provider 5 - Ilumi");
		provider5.setSocianName("Ilumi LTDA.");
		provider5.setCnpj("34.635.863/0001-57");
		provider5.setAdress("Av. Sassdcaca xanana, 588");
		provider5.setCity("Curitiba / PR");
		provider5.setPhone("+55 (88) 98877-7885");
		provider5.setHomepage("www.ilumi.com");
		provider5.setEmail("ilumi@contact.com");
		provider5.setCompanyType("SMALL");
		provider5.setAnnualBilling("UNTILL_100_MIL");
		provider5.setFundationYear(2011);
		provider5.setClientNumber(75);
		provider5.setReputation("BAD");
		provider5.setCategory("LIGHTS");
		provider5.setItems(10);
		provider5.setPercent(8);
		provider5.setDeliveryDays(7);
		provider5.setRejectionRate(15.3);
		provider5.setQtdItemsSold(150);
		providersByName.put(provider5.getId(), provider5);

		Item item = new Item();
		item.setId("121215");
		item.setName("Luminária 'LightStick' BTS ");
		item.setPhotolink("https://www.google.com.br/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi_h7XhmLHdAhWIx5AKHSfODy8QjRx6BAgBEAU&url=https%3A%2F%2Fbr.pinterest.com%2Fpin%2F612278511812709354%2F&psig=AOvVaw3ebvMPEJl0vcY98M7eoL29&ust=1536694859161257");
		item.setPrice(158.78);
		item.setProviderId(provider5.getId());
		item.setStatus("AVALIABLE");
		providersItemsById.put(item.getId(), item);
	}

	public Provider findProviderById(String id) {
		Assert.notNull(id, "The country's name must not be null");
		return providersByName.get(id);
	}

    public AllProviders getAll() {
        AllProviders all = new AllProviders();
        all.getData().addAll(providersByName.values().stream().collect(Collectors.toList()));
        return all;
    }

	public Item findItemById(String id) {
		Assert.notNull(id, "The country's name must not be null");
		return providersItemsById.get(id);
	}

    public AllItems getAllItems() {
        AllItems all = new AllItems();
        all.getData().addAll(providersItemsById.values().stream().collect(Collectors.toList()));
        return all;
    }
}
