package com.zds.grpc.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.2)",
    comments = "Source: CustomerInfoService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CustomerInfoServiceGrpc {

  private CustomerInfoServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.CustomerInfoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CustomerRequest,
      CustomerResponse> getGetCustomerInfoByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCustomerInfoByName",
      requestType = CustomerRequest.class,
      responseType = CustomerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CustomerRequest,
      CustomerResponse> getGetCustomerInfoByNameMethod() {
    io.grpc.MethodDescriptor<CustomerRequest, CustomerResponse> getGetCustomerInfoByNameMethod;
    if ((getGetCustomerInfoByNameMethod = CustomerInfoServiceGrpc.getGetCustomerInfoByNameMethod) == null) {
      synchronized (CustomerInfoServiceGrpc.class) {
        if ((getGetCustomerInfoByNameMethod = CustomerInfoServiceGrpc.getGetCustomerInfoByNameMethod) == null) {
          CustomerInfoServiceGrpc.getGetCustomerInfoByNameMethod = getGetCustomerInfoByNameMethod =
              io.grpc.MethodDescriptor.<CustomerRequest, CustomerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCustomerInfoByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CustomerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CustomerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerInfoServiceMethodDescriptorSupplier("GetCustomerInfoByName"))
              .build();
        }
      }
    }
    return getGetCustomerInfoByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<CustomerRequest,
      CustomerResponse> getSaveCustomerInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveCustomerInfo",
      requestType = CustomerRequest.class,
      responseType = CustomerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CustomerRequest,
      CustomerResponse> getSaveCustomerInfoMethod() {
    io.grpc.MethodDescriptor<CustomerRequest, CustomerResponse> getSaveCustomerInfoMethod;
    if ((getSaveCustomerInfoMethod = CustomerInfoServiceGrpc.getSaveCustomerInfoMethod) == null) {
      synchronized (CustomerInfoServiceGrpc.class) {
        if ((getSaveCustomerInfoMethod = CustomerInfoServiceGrpc.getSaveCustomerInfoMethod) == null) {
          CustomerInfoServiceGrpc.getSaveCustomerInfoMethod = getSaveCustomerInfoMethod =
              io.grpc.MethodDescriptor.<CustomerRequest, CustomerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SaveCustomerInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CustomerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CustomerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerInfoServiceMethodDescriptorSupplier("SaveCustomerInfo"))
              .build();
        }
      }
    }
    return getSaveCustomerInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CustomerInfoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceStub>() {
        @Override
        public CustomerInfoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerInfoServiceStub(channel, callOptions);
        }
      };
    return CustomerInfoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CustomerInfoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceBlockingStub>() {
        @Override
        public CustomerInfoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerInfoServiceBlockingStub(channel, callOptions);
        }
      };
    return CustomerInfoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CustomerInfoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerInfoServiceFutureStub>() {
        @Override
        public CustomerInfoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerInfoServiceFutureStub(channel, callOptions);
        }
      };
    return CustomerInfoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CustomerInfoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getCustomerInfoByName(CustomerRequest request,
        io.grpc.stub.StreamObserver<CustomerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCustomerInfoByNameMethod(), responseObserver);
    }

    /**
     */
    public void saveCustomerInfo(CustomerRequest request,
        io.grpc.stub.StreamObserver<CustomerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSaveCustomerInfoMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetCustomerInfoByNameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                CustomerRequest,
                CustomerResponse>(
                  this, METHODID_GET_CUSTOMER_INFO_BY_NAME)))
          .addMethod(
            getSaveCustomerInfoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                CustomerRequest,
                CustomerResponse>(
                  this, METHODID_SAVE_CUSTOMER_INFO)))
          .build();
    }
  }

  /**
   */
  public static final class CustomerInfoServiceStub extends io.grpc.stub.AbstractAsyncStub<CustomerInfoServiceStub> {
    private CustomerInfoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CustomerInfoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerInfoServiceStub(channel, callOptions);
    }

    /**
     */
    public void getCustomerInfoByName(CustomerRequest request,
        io.grpc.stub.StreamObserver<CustomerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCustomerInfoByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void saveCustomerInfo(CustomerRequest request,
        io.grpc.stub.StreamObserver<CustomerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSaveCustomerInfoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CustomerInfoServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CustomerInfoServiceBlockingStub> {
    private CustomerInfoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CustomerInfoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerInfoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public CustomerResponse getCustomerInfoByName(CustomerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCustomerInfoByNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public CustomerResponse saveCustomerInfo(CustomerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSaveCustomerInfoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CustomerInfoServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CustomerInfoServiceFutureStub> {
    private CustomerInfoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected CustomerInfoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerInfoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CustomerResponse> getCustomerInfoByName(
        CustomerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCustomerInfoByNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<CustomerResponse> saveCustomerInfo(
        CustomerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSaveCustomerInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CUSTOMER_INFO_BY_NAME = 0;
  private static final int METHODID_SAVE_CUSTOMER_INFO = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CustomerInfoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CustomerInfoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CUSTOMER_INFO_BY_NAME:
          serviceImpl.getCustomerInfoByName((CustomerRequest) request,
              (io.grpc.stub.StreamObserver<CustomerResponse>) responseObserver);
          break;
        case METHODID_SAVE_CUSTOMER_INFO:
          serviceImpl.saveCustomerInfo((CustomerRequest) request,
              (io.grpc.stub.StreamObserver<CustomerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class CustomerInfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CustomerInfoServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return CustomerInfoProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CustomerInfoService");
    }
  }

  private static final class CustomerInfoServiceFileDescriptorSupplier
      extends CustomerInfoServiceBaseDescriptorSupplier {
    CustomerInfoServiceFileDescriptorSupplier() {}
  }

  private static final class CustomerInfoServiceMethodDescriptorSupplier
      extends CustomerInfoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CustomerInfoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CustomerInfoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CustomerInfoServiceFileDescriptorSupplier())
              .addMethod(getGetCustomerInfoByNameMethod())
              .addMethod(getSaveCustomerInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
