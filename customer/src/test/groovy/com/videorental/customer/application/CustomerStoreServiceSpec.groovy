package com.videorental.customer.application

import com.videorental.customer.command.CreateCustomerCommand
import com.videorental.customer.infrastructure.CustomerRepositoryStub
import spock.lang.Specification

/**
 *
 * @author oleciwoj
 */
class CustomerStoreServiceSpec extends Specification {

    CustomerStoreService service = new CustomerStoreService(new CustomerRepositoryStub())

    def "add a new customer"() {
        given:
            def command = new CreateCustomerCommand("John", "Doe")
        when:
            def id = service.add(command)
        then:
            id != null
            id.id() != null
    }

    def "get customer details"() {
        given:
            def id = service.add(new CreateCustomerCommand("John", "Doe"))
        when:
            def customer = service.get(id).get()
        then:
            customer != null
            customer.id == id
            customer.firstName == "John"
            customer.lastName == 'Doe'
    }
}
