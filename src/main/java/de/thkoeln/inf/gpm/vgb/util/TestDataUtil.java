package de.thkoeln.inf.gpm.vgb.util;

import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.FilterService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.authorization.*;
import org.camunda.bpm.engine.filter.Filter;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.IdentityServiceImpl;
import org.camunda.bpm.engine.impl.persistence.entity.AuthorizationEntity;
import org.camunda.bpm.engine.task.TaskQuery;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import static org.camunda.bpm.engine.authorization.Authorization.ANY;
import static org.camunda.bpm.engine.authorization.Authorization.AUTH_TYPE_GRANT;
import static org.camunda.bpm.engine.authorization.Permissions.*;
import static org.camunda.bpm.engine.authorization.Resources.*;


public class TestDataUtil {
    private final static Logger LOGGER = Logger.getLogger(TestDataUtil.class.getName());


    private static final String[] LAST_NAMES = {"Müller", "Schmidt", "Schneider", "Fischer", "Weber", "Meier",
            "Wagner", "Becker", "Schulz", "Hoffmann"};

    private static final String[] FIRST_NAMES = {"Head", "Michael", "Thomas", "Andreas", "Stefan", "Anna",
            "Andrea", "Karin", "Monika", "Maria"};

    private static final String[] STREETS = {"Hauptstraße", "Schillerstraße", "Rathausplatz", "Bahnhofstraße",
            "Schlossallee"};


    private static <T> T randomEntry(T[] array) {
        int random = new Random().nextInt(array.length);
        return array[random];
    }

    String formatDate(Date date) {

        String dateFormatPattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);

        return dateFormat.format(date);

    }

    public void createUsers(ProcessEngine engine) {

        final IdentityServiceImpl identityService = (IdentityServiceImpl) engine.getIdentityService();

        LOGGER.info("Generating demo data for vgb process");


        // Create Demo User
        User user = identityService.newUser("demo");
        user.setFirstName("Demo");
        user.setLastName("Demo");
        user.setPassword("demo");
        user.setEmail("demo@vgb.org");
        identityService.saveUser(user, true);

        // Create Customer User
        User user1 = identityService.newUser("customer");
        user1.setFirstName("Customer");
        user1.setLastName("Customer");
        user1.setPassword("customer");
        user1.setEmail("customer@gmail.com");
        identityService.saveUser(user1, true);


        // Create Clerk User
        User user2 = identityService.newUser("clerk");
        user2.setFirstName("Clerk");
        user2.setLastName("Clerk");
        user2.setPassword("clerk");
        user2.setEmail("clerk@vgb.org");
        identityService.saveUser(user2, true);

        // Create Head User
        User user3 = identityService.newUser("head");
        user3.setFirstName("Head");
        user3.setLastName("Head");
        user3.setPassword("head");
        user3.setEmail("head@vgb.org");
        identityService.saveUser(user3, true);


        final AuthorizationService authorizationService = engine.getAuthorizationService();

        Group customerGroup = identityService.newGroup("customer");
        customerGroup.setName("Customer");
        customerGroup.setType("WORKFLOW");
        identityService.saveGroup(customerGroup);

        Group serviceGroup = identityService.newGroup("service");
        serviceGroup.setName("Service");
        serviceGroup.setType("WORKFLOW");
        identityService.saveGroup(serviceGroup);

        Group managementGroup = identityService.newGroup("management");
        managementGroup.setName("Management");
        managementGroup.setType("WORKFLOW");
        identityService.saveGroup(managementGroup);


        // create group
        if (identityService.createGroupQuery().groupId(Groups.CAMUNDA_ADMIN).count() == 0) {
            Group camundaAdminGroup = identityService.newGroup(Groups.CAMUNDA_ADMIN);
            camundaAdminGroup.setName("camunda BPM Administrators");
            camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
            identityService.saveGroup(camundaAdminGroup);
        }

        // create ADMIN authorizations on all built-in resources
        for (Resource resource : Resources.values()) {
            if (authorizationService.createAuthorizationQuery().groupIdIn(Groups.CAMUNDA_ADMIN).resourceType(resource).resourceId(ANY).count() == 0) {
                AuthorizationEntity userAdminAuth = new AuthorizationEntity(AUTH_TYPE_GRANT);
                userAdminAuth.setGroupId(Groups.CAMUNDA_ADMIN);
                userAdminAuth.setResource(resource);
                userAdminAuth.setResourceId(ANY);
                userAdminAuth.addPermission(ALL);
                authorizationService.saveAuthorization(userAdminAuth);
            }
        }


        identityService.createMembership("demo", "camunda-admin");

        identityService.createMembership("customer", "customer");
        identityService.createMembership("clerk", "service");
        identityService.createMembership("head", "management");

        // authorize groups for tasklist only:
        Authorization customerTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        customerTasklistAuth.setGroupId("customer");
        customerTasklistAuth.addPermission(ACCESS);
        customerTasklistAuth.setResourceId("tasklist");
        customerTasklistAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(customerTasklistAuth);

        Authorization customerCreateProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        customerCreateProcessDefinition.setGroupId("customer");
        customerCreateProcessDefinition.addPermission(Permissions.READ);
        customerCreateProcessDefinition.addPermission(Permissions.CREATE_INSTANCE);
        customerCreateProcessDefinition.addPermission(Permissions.READ_HISTORY);
        customerCreateProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
        customerCreateProcessDefinition.setResourceId("ProcessCustomer");
        authorizationService.saveAuthorization(customerCreateProcessDefinition);

        Authorization customerCreateProcessInstance = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        customerCreateProcessInstance.setGroupId("customer");
        customerCreateProcessInstance.addPermission(Permissions.CREATE);
        customerCreateProcessInstance.setResource(Resources.PROCESS_INSTANCE);
        customerCreateProcessInstance.setResourceId("*");
        authorizationService.saveAuthorization(customerCreateProcessInstance);

        Authorization serviceTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        serviceTasklistAuth.setGroupId("service");
        serviceTasklistAuth.addPermission(ACCESS);
        serviceTasklistAuth.setResourceId("tasklist");
        serviceTasklistAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(serviceTasklistAuth);

        Authorization serviceReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        serviceReadProcessDefinition.setGroupId("service");
        serviceReadProcessDefinition.addPermission(Permissions.READ);
        serviceReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
        serviceReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
        authorizationService.saveAuthorization(serviceReadProcessDefinition);

        Authorization managementTasklistAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        managementTasklistAuth.setGroupId("management");
        managementTasklistAuth.addPermission(ACCESS);
        managementTasklistAuth.setResourceId("tasklist");
        managementTasklistAuth.setResource(APPLICATION);
        authorizationService.saveAuthorization(managementTasklistAuth);

        Authorization managementReadProcessDefinition = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        managementReadProcessDefinition.setGroupId("management");
        managementReadProcessDefinition.addPermission(Permissions.READ);
        managementReadProcessDefinition.addPermission(Permissions.READ_HISTORY);
        managementReadProcessDefinition.setResource(Resources.PROCESS_DEFINITION);
        authorizationService.saveAuthorization(managementReadProcessDefinition);

        Authorization customerCustomerAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        customerCustomerAuth.setGroupId("customer");
        customerCustomerAuth.setResource(USER);
        customerCustomerAuth.setResourceId("customer");
        customerCustomerAuth.addPermission(READ);
        authorizationService.saveAuthorization(customerCustomerAuth);

        Authorization customerDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        customerDemoAuth.setGroupId("customer");
        customerDemoAuth.setResource(USER);
        customerDemoAuth.setResourceId("demo");
        customerDemoAuth.addPermission(READ);
        authorizationService.saveAuthorization(customerDemoAuth);

        Authorization serviceDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        serviceDemoAuth.setGroupId("service");
        serviceDemoAuth.setResource(USER);
        serviceDemoAuth.setResourceId("demo");
        serviceDemoAuth.addPermission(READ);
        authorizationService.saveAuthorization(serviceDemoAuth);

        Authorization serviceClerkAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        serviceClerkAuth.setGroupId("service");
        serviceClerkAuth.setResource(USER);
        serviceClerkAuth.setResourceId("clerk");
        serviceClerkAuth.addPermission(READ);
        authorizationService.saveAuthorization(serviceClerkAuth);

        Authorization manDemoAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        manDemoAuth.setGroupId("management");
        manDemoAuth.setResource(USER);
        manDemoAuth.setResourceId("demo");
        manDemoAuth.addPermission(READ);
        authorizationService.saveAuthorization(manDemoAuth);

        Authorization manHeadAuth = authorizationService.createNewAuthorization(AUTH_TYPE_GRANT);
        manHeadAuth.setGroupId("management");
        manHeadAuth.setResource(USER);
        manHeadAuth.setResourceId("head");
        manHeadAuth.addPermission(READ);
        authorizationService.saveAuthorization(manHeadAuth);

        // create default filters


        FilterService filterService = engine.getFilterService();
        Map<String, Object> filterProperties = new HashMap<>();

        filterProperties.put("description", "Tasks assigned to me");
        filterProperties.put("priority", -10);
        addVariables(filterProperties);
        TaskService taskService = engine.getTaskService();
        TaskQuery query = taskService.createTaskQuery().taskAssigneeExpression("${currentUser()}");
        Filter myTasksFilter = filterService.newTaskFilter().setName("My Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
        filterService.saveFilter(myTasksFilter);

        filterProperties.clear();
        filterProperties.put("description", "Tasks assigned to my Groups");
        filterProperties.put("priority", -5);
        addVariables(filterProperties);
        query = taskService.createTaskQuery().taskCandidateGroupInExpression("${currentUserGroups()}").taskUnassigned();
        Filter groupTasksFilter = filterService.newTaskFilter().setName("My Group Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
        filterService.saveFilter(groupTasksFilter);

        // global read authorizations for these filters

        Authorization globalMyTaskFilterRead = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
        globalMyTaskFilterRead.setResource(FILTER);
        globalMyTaskFilterRead.setResourceId(myTasksFilter.getId());
        globalMyTaskFilterRead.addPermission(READ);
        authorizationService.saveAuthorization(globalMyTaskFilterRead);

        Authorization globalGroupFilterRead = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GLOBAL);
        globalGroupFilterRead.setResource(FILTER);
        globalGroupFilterRead.setResourceId(groupTasksFilter.getId());
        globalGroupFilterRead.addPermission(READ);
        authorizationService.saveAuthorization(globalGroupFilterRead);

        // management filter

        // all tasks
        filterProperties.clear();
        filterProperties.put("description", "All Tasks - Not recommended to be used in production :)");
        filterProperties.put("priority", 10);
        addVariables(filterProperties);
        query = taskService.createTaskQuery();
        Filter allTasksFilter = filterService.newTaskFilter().setName("All Tasks").setProperties(filterProperties).setOwner("demo").setQuery(query);
        filterService.saveFilter(allTasksFilter);

    }

    protected void addVariables(Map<String, Object> filterProperties) {
        List<Object> variables = new ArrayList<Object>();
        filterProperties.put("variables", variables);
    }

    protected void addVariable(List<Object> variables, String name, String label) {
        Map<String, String> variable = new HashMap<String, String>();
        variable.put("name", name);
        variable.put("label", label);
        variables.add(variable);
    }
}
