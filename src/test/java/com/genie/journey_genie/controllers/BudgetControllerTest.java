package com.genie.journey_genie.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.genie.journey_genie.models.Budget;
import com.genie.journey_genie.models.BudgetRepository;
import com.genie.journey_genie.models.User;

@WebMvcTest(BudgetController.class)
public class BudgetControllerTest {

    @MockBean
    private BudgetRepository budgetRepository;

    @Autowired
    private MockMvc mockMvc;

    

    @Test
        public void testgetAllBudgets() throws Exception {
            Budget budget = new Budget();
            budget.setItem("Test Item");
            budget.setAmount(100.0);

            User user = new User();
            List<Budget> budgets = new ArrayList<Budget>();
            budgets.add(budget);

            Mockito.when(budgetRepository.findAll()).thenReturn(budgets);
            //Mockito.when(budgetRepository.findByUser(Mockito.any(User.class), Mockito.any())).thenReturn(budgets);

            mockMvc.perform(MockMvcRequestBuilders.get("/budgets"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
                    //.andExpect(MockMvcResultMatchers.content().json("[{\"item\":\"Test Item\",\"amount\":100.0}]"));
        }

    @Test
    public void testCreateBudget() throws Exception {
        Budget budget = new Budget();
        budget.setItem("New Item");
        budget.setAmount(200.0);

        Mockito.when(budgetRepository.save(Mockito.any(Budget.class))).thenReturn(budget);

        String requestBody = "{\"item\":\"New Item\",\"amount\":200.0}";

        mockMvc.perform(MockMvcRequestBuilders.post("/budgets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.item").value("New Item"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(200.0));
    }

    @Test
    public void testDeleteBudget() throws Exception {
        Budget budget = new Budget();
        budget.setId(1L);
        budget.setItem("Test Item");
        budget.setAmount(100.0);

        Mockito.when(budgetRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(budget));

        mockMvc.perform(MockMvcRequestBuilders.delete("/budgets/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // @Test
    // public void testShowBudgetPage() throws Exception {
    //     mockMvc.perform(MockMvcRequestBuilders.get("/track-budget"))
    //             .andExpect(MockMvcResultMatchers.status().isOk())
    //             .andExpect(MockMvcResultMatchers.view().name("budgets/show"))
    //             .andExpect(MockMvcResultMatchers.model().attributeExists("budget"));
    // }
}
