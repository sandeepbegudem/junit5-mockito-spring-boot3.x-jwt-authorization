package com.sandeepbegudem.customer.payments.service.controller;import com.sandeepbegudem.customer.payments.service.dto.CustomerPaymentsRequest;import com.sandeepbegudem.customer.payments.service.dto.CustomerResponse;import com.sandeepbegudem.customer.payments.service.exception.CustomerNotFoundException;import com.sandeepbegudem.customer.payments.service.service.CustomerService;import com.sandeepbegudem.customer.payments.service.service.JwtService;import io.swagger.v3.oas.annotations.Operation;import io.swagger.v3.oas.annotations.media.Content;import io.swagger.v3.oas.annotations.media.Schema;import io.swagger.v3.oas.annotations.responses.ApiResponse;import io.swagger.v3.oas.annotations.responses.ApiResponses;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.security.authentication.AuthenticationManager;import org.springframework.web.bind.annotation.*;import java.util.List;@RestController@RequestMapping("/api/v1/customers")public class CustomerController {    private CustomerService customerService;    private JwtService jwtService;    private AuthenticationManager authenticationManager;    @Autowired    public CustomerController(CustomerService customerService, JwtService jwtService, AuthenticationManager authenticationManager) {        this.customerService = customerService;        this.jwtService = jwtService;        this.authenticationManager = authenticationManager;    }    @Operation(summary = "Save a customer using the customer request body",            description = "Endpoint saves a customer to the database")    @ApiResponses({            @ApiResponse(responseCode = "200", description = "saved customer to the db",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "400", description = "bad request",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "500", description = "Internal Server Error",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")})})    @PostMapping    @PreAuthorize("hasAuthority('USER')")    public ResponseEntity<CustomerResponse> insertCustomer(@RequestBody CustomerPaymentsRequest request){        return new ResponseEntity<>(customerService.saveCustomer(request), HttpStatus.CREATED);    }    @Operation(summary = "Get all customers",            description = "Endpoint returns list of all customers")    @ApiResponses({            @ApiResponse(responseCode = "200", description = "retrieved list of all customers",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "400", description = "bad request",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "404", description = "not found",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")})})    @GetMapping    @PreAuthorize("hasAuthority('ADMIN')")    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);    }    @Operation(summary = "Get a customer by id",            description = "Input Key and it will return relevant data")    @ApiResponses({            @ApiResponse(responseCode = "200", description = "found the customer",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "400", description = "bad request",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "404", description = "customer not found",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")})})    @GetMapping("/{id}")    @PreAuthorize("hasAuthority('USER')")    public ResponseEntity<CustomerResponse> retrieveCustomerById(@PathVariable Integer id) throws CustomerNotFoundException{        return new ResponseEntity<>(customerService.customerById(id), HttpStatus.OK);    }    @Operation(summary = "Delete a customer by id",            description = "Endpoint deletes a customer by using the id and doesn't return anything")    @ApiResponses({            @ApiResponse(responseCode = "200", description = "deleted customer",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "400", description = "bad request",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")}),            @ApiResponse(responseCode = "404", description = "customer not found",                    content = {@Content(schema = @Schema(implementation = String.class),                            mediaType = "application/json")})})    @ResponseStatus(HttpStatus.NO_CONTENT)    @DeleteMapping("/{id}")    @PreAuthorize("hasAuthority('USER')")    public void deleteCustomerById(@PathVariable Integer id) throws CustomerNotFoundException {        if (null != id) customerService.deleteCustomerById(id);        else            throw new RuntimeException("id : " + id + " can't be null");    }}