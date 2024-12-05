<script lang="ts">
	import * as Form from '$lib/components/ui/form';
	import { superForm, type Infer, type SuperValidated } from 'sveltekit-superforms';
	import { zodClient } from 'sveltekit-superforms/adapters';

	import { Input } from '$lib/components/ui/input/index.js';
	import { registerSchema, type RegisterSchema } from '$lib/schema';

	import * as AlertDialog from '../ui/alert-dialog';
	import { redirect } from '@sveltejs/kit';

	export let data: SuperValidated<Infer<RegisterSchema>>;

	const form = superForm(data, {
		validators: zodClient(registerSchema)
	});

	const { form: formData, enhance } = form;

	export let isDialogOpen = false;

</script>

<form method="POST" use:enhance>
	<div class="grid gap-4">
		<Form.Field class="grid gap-2" {form} name="username">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Username</Form.Label>
					<Input placeholder="Username" {...props} bind:value={$formData.username} />
				{/snippet}
			</Form.Control>
		</Form.Field>
		<Form.Field class="grid gap-2" {form} name="email">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Email</Form.Label>
					<Input placeholder="Email" {...props} bind:value={$formData.email} />
				{/snippet}
			</Form.Control>
		</Form.Field>
		<Form.Field class="grid gap-2" {form} name="password">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Password</Form.Label>
					<Input
						type="password"
						placeholder="Password"
						{...props}
						bind:value={$formData.password}
					/>
				{/snippet}
			</Form.Control>
		</Form.Field>
		<Form.Field class="grid gap-2" {form} name="passwordConfirm">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Confirm password</Form.Label>
					<Input
						type="password"
						placeholder="Confirm password"
						{...props}
						bind:value={$formData.passwordConfirm}
					/>
				{/snippet}
			</Form.Control>
		</Form.Field>
		<Form.Field class="grid gap-2" {form} name="inviteToken">
			<Form.Control>
				{#snippet children({ props })}
					<Form.Label>Invite Token</Form.Label>
					<Input placeholder="Invite Token" {...props} bind:value={$formData.inviteToken} />
				{/snippet}
			</Form.Control>
			<Form.Button type="submit" class="w-full">Sign Up</Form.Button>
		</Form.Field>
	</div>
</form>

<AlertDialog.Root open={isDialogOpen}>
	<AlertDialog.Trigger></AlertDialog.Trigger>
	<AlertDialog.Content>
		<AlertDialog.Header>
			<AlertDialog.Title>Login Success</AlertDialog.Title>
			<AlertDialog.Description>
				Your account has been created. You will be redirected to the login page.
			</AlertDialog.Description>
		</AlertDialog.Header>
		<AlertDialog.Footer>
			<AlertDialog.Action onclick={() => redirect(302, '/login')}>OK</AlertDialog.Action>
		</AlertDialog.Footer>
	</AlertDialog.Content>
</AlertDialog.Root>
