package unit.com.bitdubai.fermat_osa_addon.layer.android.database_system.developer.bitdubai.version_1.structure.AndroidDatabase;

import android.app.Activity;

import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_osa_addon.layer.android.database_system.developer.bitdubai.version_1.structure.AndroidDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.UUID;

import unit.com.bitdubai.fermat_osa_addon.layer.android.database_system.developer.bitdubai.version_1.CustomBuildConfig;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by jorgegonzalez on 2015.06.27..
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = CustomBuildConfig.class)
public class CreateDatabaseTest {

    private Activity mockActivity;
    private String mockContext;

    private AndroidDatabase testDatabase;
    private String testDatabaseName = "testDatabase";


    @Before
    public void CreateDatabase_TheDatabaseHasNotBeenCreated_MethodInvokedSuccessfully() throws Exception {
        mockActivity = Robolectric.setupActivity(Activity.class);
        mockContext = "test1"; //shadowOf(mockActivity).getApplicationContext();

        testDatabase = new AndroidDatabase(mockContext, UUID.randomUUID(), testDatabaseName);
        catchException(testDatabase).createDatabase(testDatabaseName);
        assertThat(caughtException()).isNull();
    }

    @Test
    public void CreateDatabase_TheDatabaseHasAlreadyBeenCreated_ThrowsCantCreateDatabaseException() throws Exception {
        mockActivity = Robolectric.setupActivity(Activity.class);
        mockContext = "test1"; //shadowOf(mockActivity).getApplicationContext();

        testDatabase = new AndroidDatabase(mockContext, UUID.randomUUID(), testDatabaseName);
        testDatabase.createDatabase(testDatabaseName);
        catchException(testDatabase).createDatabase(testDatabaseName);
        assertThat(caughtException()).isInstanceOf(CantCreateDatabaseException.class);

    }
}