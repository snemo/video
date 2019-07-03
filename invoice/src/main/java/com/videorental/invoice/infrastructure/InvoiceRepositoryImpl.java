package com.videorental.invoice.infrastructure;

import com.videorental.common.rental.OrderId;
import com.videorental.invoice.domain.InvoiceIssued;
import com.videorental.invoice.domain.InvoiceRepository;
import com.videorental.invoice.dto.InvoiceDto;
import com.videorental.invoice.exception.InvoiceException;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.videorental.db.generated.tables.Invoices.INVOICES;

/**
 * @author oleciwoj
 */
@Repository
@AllArgsConstructor
class InvoiceRepositoryImpl implements InvoiceRepository {

    private final DSLContext dsl;

    @Override
    public void save(InvoiceIssued invoice) {
        int rows = dsl.insertInto(INVOICES)
                .set(INVOICES.INVOICE_ID, invoice.getId().id())
                .set(INVOICES.CUSTOMER_ID, invoice.getCustomerId().id())
                .set(INVOICES.ORDER_ID, invoice.getOrderId().id())
                .set(INVOICES.TOTAL_PRICE, invoice.totalPrice().getAmount())
                .execute();

        if (rows != 1)
            throw new InvoiceException("Could not write invoice into DB: " + invoice);
    }

    @Override
    public Optional<InvoiceDto> findByOrderId(OrderId orderId) {
        return dsl.selectFrom(INVOICES)
                .where(INVOICES.ORDER_ID.eq(orderId.id()))
                .fetchOptional()
                .map(InvoiceDto::of);
    }
}
