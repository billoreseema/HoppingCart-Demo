package service;

import model.FinalInvoice;
import model.Offer;
import model.Product;
import model.ProductInvoice;
import repository.OfferRepository;
import repository.ProductInvoiceRepository;
import repository.ProductRepository;


public class ProductInvoiceServiceImpl implements ProductInvoiceService {

    private OfferRepository offerRepository;
    private ProductRepository productRepository;

    private ProductInvoiceRepository productInvoiceRepository;

    public ProductInvoiceServiceImpl(OfferRepository offerRepository, ProductRepository productRepository, ProductInvoiceRepository productInvoiceRepository) {
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
        this.productInvoiceRepository = productInvoiceRepository;
    }

    @Override
    public ProductInvoice scanProduct(String productId , FinalInvoice finalInvoice) {
        Product product = productRepository.getProduct(productId);
        try {

            if (product == null) {
                System.out.println("Invalid Product or Product is out of stock");
            }

            Offer offer = offerRepository.getOffer(productId);

            ProductInvoice productInvoice = productInvoiceRepository.getProductInvoice(productId);

            if (productInvoice == null) {
                productInvoice = new ProductInvoice();
                productInvoice.setProductDetails(product);
                productInvoiceRepository.addProductInvoice(productInvoice);
            }

            productInvoice.setProductQty(productInvoice.getProductQty() + 1);

            if (offer != null)
                applyOffers(productInvoice, offer);
            productInvoice.setTotalPrice();

            finalInvoice.setProductInvoiceList(productInvoiceRepository.getFinalProductInvoice());
            finalInvoice.setGrandTotal(finalInvoice.getProductInvoiceList().stream().mapToDouble(productInvoice1 -> productInvoice1.getTotalPrice()).sum());
            return productInvoice;
        } catch (Exception ex) {
           // System.out.println(ex.getMessage());
            return null;
        }

    }

    @Override
    public void clearInvoice() {
        productInvoiceRepository.clearInvoice();
    }

    private void applyOffers(ProductInvoice productInvoice, Offer offer) {

        if (productInvoice.getProductQty() % (offer.getProductQty()) == 0) {
            int mul = productInvoice.getProductQty() / (offer.getProductQty());
            double discountedPrice = (productInvoice.getUnitPrice() - offer.getOfferedUnitPrice()) * offer.getProductQty();
            productInvoice.setDiscountedPrice(discountedPrice * mul);
        }
    }

}
